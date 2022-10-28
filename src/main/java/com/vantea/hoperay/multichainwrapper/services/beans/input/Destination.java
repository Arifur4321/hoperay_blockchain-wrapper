package com.vantea.hoperay.multichainwrapper.services.beans.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Destination {
		@NotNull
		@NotEmpty
		private String address_to;

		@NotNull
		@NotEmpty
		@Valid
		private List<VoucherQuantity> transfer;

		public String getAddress_to() {
			return address_to;
		}

		public void setAddress_to(String address_to) {
			this.address_to = address_to;
		}

		public List<VoucherQuantity> getTransfer() {
			return transfer;
		}

		public void setTransfer(List<VoucherQuantity> transfer) {
			this.transfer = transfer;
		}

		@Override
		public String toString() {
			return "Destinations [address_to=" + address_to + ", transfer=" + transfer + "]";
		}

	}