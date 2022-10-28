/**
 * Bean containing respose for MultiChain "listpermissions" JSON-RCP API 
 */
package com.vantea.hoperay.multichainwrapper.services.beans;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MCListpermissions {
	
	private List<Permission> result;
	
	public List<Permission> getPermission() {
		return result;
	}

	public void setPermission(List<Permission> permission) {
		this.result = permission;
	}
	
	@Override
	public String toString() {
		return "MCListpermissions [permission=" + result + "]";
	}

	public class Permission {

		@SerializedName(value = "address")
		private String mcaddress;

		@SerializedName(value = "for")
		private String mcfor;

		@SerializedName(value = "type")
		private String mctype;

		@SerializedName(value = "startblock")
		private long startblock_;

		@SerializedName(value = "endblock")
		private long endblock_;

		public String getMcaddress() {
			return mcaddress;
		}

		public void setMcaddress(String mcaddress) {
			this.mcaddress = mcaddress;
		}

		public String getMcfor() {
			return mcfor;
		}

		public void setMcfor(String mcfor) {
			this.mcfor = mcfor;
		}

		public String getMctype() {
			return mctype;
		}

		public void setMctype(String mctype) {
			this.mctype = mctype;
		}

		public long getStartblock_() {
			return startblock_;
		}

		public void setStartblock_(long startblock_) {
			this.startblock_ = startblock_;
		}

		public long getEndblock_() {
			return endblock_;
		}

		public void setEndblock_(long endblock_) {
			this.endblock_ = endblock_;
		}

		@Override
		public String toString() {
			return "Permission [mcaddress=" + mcaddress + ", mcfor=" + mcfor + ", mctype=" + mctype + ", startblock_="
					+ startblock_ + ", endblock_=" + endblock_ + "]";
		}

	}

}
