/**
 * Class with utility methods
 */
package com.vantea.hoperay.multichainwrapper.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bitcoinj.core.Base58;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vantea.hoperay.multichainwrapper.services.beans.output.KeyPairAndAddress;

public class Utils {

	/**
	 * Calculates KeyPairs and address starting from a string seed. Multichain must
	 * be configured to be compatible with Bitcoin setting these values in
	 * "params.dat": - address-pubkeyhash-version=00 - address-scripthash-version=05
	 * - private-key-version=80 - address-checksum-value=00000000
	 * 
	 * Returns a String[] with private key [0], public key [1], and address [2]
	 * 
	 * @param seed - Optional parameter used to generate keypairs. Same seed will
	 *             generate same keys.
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 */
	public static KeyPairAndAddress generateKeyPair(String seed)
			throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchProviderException {

		// Initialize keypair using optional seed
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA");
		ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
		SecureRandom sr = null;
		if (seed == null || seed.equals("")) {
			sr = new SecureRandom();
		} else {
			sr = new SecureRandom(seed.getBytes());
		}
		keyGen.initialize(ecSpec, sr);

		KeyPair kp = keyGen.generateKeyPair();
		PublicKey pub = kp.getPublic();
		PrivateKey pvt = kp.getPrivate();

		ECPrivateKey epvt = (ECPrivateKey) pvt;
		String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();

		ECPublicKey epub = (ECPublicKey) pub;
		ECPoint pt = epub.getW();
		String sx = adjustTo64(pt.getAffineX().toString(16));

		// Compressed public key
		String bcPub = "";
		if ((pt.getAffineY().longValue() % 2) == 0) {
			bcPub = "02" + sx;
		} else {
			bcPub = "03" + sx;
		}

		// Calculate address starting from public key
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] s1 = sha.digest(hexStringToByteArray(bcPub));

		MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
		byte[] r1 = rmd.digest(s1);

		byte[] r2 = new byte[r1.length + 1];
		r2[0] = 0;
		for (int i = 0; i < r1.length; i++)
			r2[i + 1] = r1[i];

		byte[] s2 = sha.digest(r2);
		byte[] s3 = sha.digest(s2);

		byte[] a1 = new byte[25];
		for (int i = 0; i < r2.length; i++) {
			a1[i] = r2[i];
		}

		byte[] x1 = new byte[4];
		for (int x = 0; x < x1.length; x++) {
			x1[x] = s3[x];
		}

		for (int y = 0; y < x1.length; y++) {
			a1[21 + y] = s3[y];
		}

		String address = Base58.encode(a1);

		// Calculate private key
		String sepvt_01 = sepvt + "01";
		String extPKEY = "80" + sepvt_01;
		byte[] shaP1 = sha.digest(hexStringToByteArray(extPKEY));
		byte[] shaP2 = sha.digest(shaP1);
		byte[] checkSumP2 = new byte[4];
		for (int xp = 0; xp < x1.length; xp++) {
			checkSumP2[xp] = shaP2[xp];
		}
		String extPKEY_CKS = extPKEY + bytesToHex(checkSumP2).toUpperCase();
		String pkey = Base58.encode(hexStringToByteArray(extPKEY_CKS));

		KeyPairAndAddress kpa = new KeyPairAndAddress(pkey, bcPub, address);

		return kpa;
	}

	/**
	 * Adjust a string length to 64 chars
	 * 
	 * @param s
	 * @return
	 */
	public static String adjustTo64(String s) {
		switch (s.length()) {
		case 62:
			return "00" + s;
		case 63:
			return "0" + s;
		case 64:
			return s;
		default:
			throw new IllegalArgumentException("not a valid key: " + s);
		}
	}

	/**
	 * Convert a hexadecimal string to a byte array
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * Convert a byte array to a hexadecimal string
	 * 
	 * @param hashInBytes
	 * @return
	 */
	public static String bytesToHex(byte[] hashInBytes) {

		StringBuilder sb = new StringBuilder();
		for (byte b : hashInBytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();

	}

	/**
	 * Calculate an address using a private key
	 * @param privateKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public static String calculateAddressFromPrivateKey(String privateKey) throws NoSuchAlgorithmException, NoSuchProviderException {
		// Recalculate Sender address using private key
		NetworkParameters np = MainNetParams.get();
		ECKey epvt = DumpedPrivateKey.fromBase58(np, privateKey).getKey();

		// Public key using bitcoinj library
		String bcPub = Utils.bytesToHex(epvt.getPubKey());
		
		// Now calculate address
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] s1 = sha.digest(Utils.hexStringToByteArray(bcPub));

		MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
		byte[] r1 = rmd.digest(s1);

		byte[] r2 = new byte[r1.length + 1];
		r2[0] = 0;
		for (int i = 0; i < r1.length; i++)
			r2[i + 1] = r1[i];

		byte[] s2 = sha.digest(r2);
		byte[] s3 = sha.digest(s2);

		byte[] a1 = new byte[25];
		for (int i = 0; i < r2.length; i++) {
			a1[i] = r2[i];
		}

		byte[] x1 = new byte[4];
		for (int x = 0; x < x1.length; x++) {
			x1[x] = s3[x];
		}

		for (int y = 0; y < x1.length; y++) {
			a1[21 + y] = s3[y];
		}

		String address = Base58.encode(a1);
		
		return address;
	}
	
	/**
	 * Extract transaction vins and vouts
	 * @param decodedTx
	 * @return
	 */
	public static Map<String, Integer> extractVINsTxIDsAndVoutIDX(String decodedTx) {
		
		Map<String, Integer> txAndVout = new LinkedHashMap<String, Integer>();
		
		JsonArray vins = JsonParser.parseString(decodedTx).getAsJsonObject().get("vin").getAsJsonArray();
		Iterator<JsonElement> vinsIterator = vins.iterator();
		while (vinsIterator.hasNext()) {
			JsonObject vin = vinsIterator.next().getAsJsonObject();
			txAndVout.put(vin.get("txid").getAsString() + "|" + vin.get("vout").getAsInt(), vin.get("vout").getAsInt());
		}
		
		return txAndVout;
	}

}
