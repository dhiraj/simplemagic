package com.j256.simplemagic;

/**
 * Content details associated with a file or bytes, returned by the magic matching code in
 * {@link ContentInfoUtil#findMatch(String)} and other methods.
 * 
 * @author graywatson
 */
public class ContentInfo {

	private final ContentType contentType;
	private final String name;
	private final String message;
	private final String mimeType;

	public ContentInfo(String name, String mimeType, String message) {
		this.contentType = ContentType.fromMimeType(mimeType);
		if (this.contentType == ContentType.UNKNOWN) {
			this.name = name;
		} else {
			this.name = this.contentType.getSimpleName();
		}
		this.mimeType = mimeType;
		this.message = message;
	}

	/**
	 * Returns the internal enumerated type associated with the content or {@link ContentType#UNKNOWN} if not known.
	 */
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * Returns the short name of the content either from the content-type or extracted from the message. If the
	 * content-type is known then this is a specific name string. Otherwise this is usually the first word of the
	 * message generated by the magic file.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the mime-type or null if none.
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * Returns the full message as generated by the magic matching code. This should be similar to the output from the
	 * Unix file(1) command.
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		if (contentType != null) {
			sb.append(", type ").append(contentType);
		}
		if (mimeType != null) {
			sb.append(", mime '").append(mimeType).append('\'');
		}
		sb.append(", msg '").append(message).append('\'');
		return sb.toString();
	}
}
