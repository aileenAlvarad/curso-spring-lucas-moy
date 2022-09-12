package com.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayerMedia {

	private static List<String> videos = new ArrayList<>();

	public static void main(String[] args) {
		File dir = new File("C:\\Users\\Aileen\\Videos\\videos2");
		baseFolder(dir.listFiles());
		showFiles(dir.listFiles());
		System.out.println(generateHTML());
	}

	private static void baseFolder(File[] files) {
		for (File file : files) {
			if (file.isFile()) {
				videos.add(file.getAbsolutePath());
//				System.out.println("File: " + file.getAbsolutePath());
			}
		}
	}

	private static void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
//				System.out.println("Directory: " + file.getAbsolutePath());
				showFiles(file.listFiles()); // Calls same method again.
			} else {
				videos.add(file.getAbsolutePath());
//				System.out.println("File: " + file.getAbsolutePath());
			}
		}
	}
	
	private static String generateHTML() {
		String html ="<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<body>\r\n"
				+ "\r\n"
				+ "<script>\r\n";
				
				
		String videosString = "var videosArray =[";
		for (String vid :videos) {
			videosString += ",\""+vid+"\"";
		}
		videosString = videosString.replaceFirst(",", "")+"];";
		html += videosString;
		html +=   "\r\n"
				+ "function changeVid(increase){\r\n"
				+ "	var indexElement = document.getElementById(\"index\");\r\n"
				+ "	indexElement.value = + indexElement.value +increase;\r\n"
				+ "	var video = document.getElementById(\"video\");\r\n"
				+ "	var videoPlayerHTML = `<video width=\"320\" height=\"240\" controls id=\"video-handler\">\r\n"
				+ "	  <source id=\"video-player\" src=\"${videosArray[indexElement.value]}\" type=\"video/mp4\">\r\n"
				+ "	  Your browser does not support the video tag. Genedado\r\n"
				+ "	</video>`;\r\n"
				+ "	video.innerHTML = videoPlayerHTML;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "function changeSpeed(speed){\r\n"
				+ "	document.querySelector('video').playbackRate  = speed;\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "</script>\r\n"
				+ "\r\n"
				+ "<input type=\"hidden\" id=\"index\" value=\"0\">\r\n"
				+ "\r\n"
				+ "<div id=\"video\">\r\n"
				+ "	<video width=\"320\" height=\"240\" controls id=\"video-handler\">\r\n"
				+ "	  <source id=\"video-player\" src=\"https://www.w3schools.com/html/movie.mp4\" type=\"video/mp4\">\r\n"
				+ "	  Your browser does not support the video tag.\r\n"
				+ "	</video>\r\n"
				+ "</div>\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "<input type=\"button\" value=\"Previos\" onclick=\"changeVid(-1)\">\r\n"
				+ "<input type=\"button\" value=\"Next\" onclick=\"changeVid(1)\">\r\n"
				+ "<input type=\"button\" value=\"X2\" onclick=\"changeSpeed(2.1)\">\r\n"
				+ "<input type=\"button\" value=\"X3\" onclick=\"changeSpeed(3.5)\">\r\n"
				+ "</body>\r\n"
				+ "</html>\r\n"
				+ "";
		return html;	
	}
}
