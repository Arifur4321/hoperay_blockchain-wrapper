package com.vantea.hoperay.multichainwrapper.multichain;

import java.util.ArrayList;
import java.util.List;

public class RestRequestBody {

	private String method;
	private List<Object> params ;
	//list<Object> object = new ArrayList<Object>();
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<Object> getParams() {
		return params;
	}

	public void setParams(List<Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "InputBody [method=" + method + ", params=" + params + "]";
	}

}
