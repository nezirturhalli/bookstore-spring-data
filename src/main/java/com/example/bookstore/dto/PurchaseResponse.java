package com.example.bookstore.dto;

import java.util.Objects;

public class PurchaseResponse {

	private final String status;

	public PurchaseResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PurchaseResponse that = (PurchaseResponse) o;
		return Objects.equals(status, that.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(status);
	}
}
