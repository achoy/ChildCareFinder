package org.childcare;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.*;
import java.nio.file.*;

import org.restlet.data.MediaType;

public class Helpers {

	private static String _webPath = "";
	
	public static void setWebPath(String webPage)
	{
		_webPath = webPage;
	}
	
	public static String getLocalPath(String path, String filename)
	{
		return combinePath(_webPath, path + filename);
	}
	
	public static boolean fileExist(String path)
	{
		return (path != null) && (new File(path).isFile());
	}
	
	public static String combinePath(String part1, String part2)
	{
		if (part1 == null)
			return part2;
		if (part2 == null)
			return part1;

		if (part1.endsWith(File.separator))
			part1 = part1.substring(0, part1.length() - 2);
		if (part2.startsWith(File.separator))
			part2 = part2.substring(1);

		return part1 + File.separator + part2;
	}
	
	public static MediaType resolveMediaType(String filename)
	{
		filename = filename.toLowerCase();
		MediaType media = MediaType.ALL;

		if (filename.endsWith(".html") || filename.endsWith(".htm"))
			media = MediaType.TEXT_HTML;
		else if (filename.endsWith(".js"))
			media = MediaType.APPLICATION_JAVASCRIPT;
		else if (filename.endsWith(".jpg") || filename.endsWith(".jpeg"))
			media = MediaType.IMAGE_JPEG;
		else if (filename.endsWith(".png"))
			media = MediaType.IMAGE_PNG;
		else if (filename.endsWith(".ico"))
			media = MediaType.IMAGE_ICON;
		else if (filename.endsWith(".css"))
			media = MediaType.TEXT_CSS;

		return media;
	}
	
	/**
	 * Reads a file into a string
	 * 
	 * @param path
	 *            Path to the file
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding)
			throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	/**
	 * Reads a file into a string using Charset.defaultCharset()
	 * 
	 * @param path
	 *            Path to the file
	 * @return
	 * @throws IOException
	 */
	public static String readFile(String path) throws IOException
	{
		return readFile(path, StandardCharsets.UTF_8);
	}
	
	/**
	 * Similar in functionality to C# string.IsNullOrEmpty
	 * 
	 * @param s
	 * @return True when "s" is null, empty, or whitespace-only
	 */
	public static Boolean isNullOrEmpty(String s)
	{
		return s == null || s.trim().length() == 0;
	}
}
