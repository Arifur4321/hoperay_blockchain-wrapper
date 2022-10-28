/**
 * Bean for generic wrapper response
 */
package com.vantea.hoperay.multichainwrapper.services.beans.output;

public class WrapperReturn {

	public Object result;
	public Object error;
	public Object id;

	public WrapperReturn(Object result, Object error, Object id) {
		super();
		this.result = result;
		this.error = error;
		this.id = id;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WrapperReturn [result=" + result + ", error=" + error + ", id=" + id + "]";
	}

}
