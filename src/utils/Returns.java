package utils;

public enum Returns {

	BAD_REQUEST(400),
	CONFLICT(409),
	UNAUTHORIZED(401),
	INTERNAL_SERVER_ERROR(500);

	private int response;

	Returns(int response) {
		this.response = response;
	}

	public int getResponse() {
		return response;
	}
}
