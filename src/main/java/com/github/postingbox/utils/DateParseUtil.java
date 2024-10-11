package com.github.postingbox.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParseUtil {

	private DateParseUtil() {
	}

	public static LocalDateTime parse(String text) {
		// 먼저 날짜와 시간을 분리
		String[] dateTimeArr = text.split(" ");
		String dateText = dateTimeArr[0]; // 날짜 부분
		String timeText = dateTimeArr.length > 1 ? dateTimeArr[1] : "00:00"; // 시간이 없으면 00:00으로 처리

		// 날짜 부분 처리
		String regex = findRegex(dateText);
		String[] dateArr = dateText.split(regex);

		LocalDateTime dateTime = LocalDateTime.of(
			addYear(toInt(dateArr[0])), // 연도
			toInt(dateArr[1]),          // 월
			toInt(dateArr[2]),          // 일
			toInt(timeText.split(":")[0]),  // 시간
			toInt(timeText.split(":")[1])   // 분
		);

		return dateTime;
	}

	// TODO 연도 계산 코드 다시 짜기
	private static int addYear(int year) {
		return year < 2000 ? year + 2000 : year;
	}

	private static String findRegex(String text) {
		if (text.contains(".")) {
			return "\\.";
		}
		if (text.contains("-")) {
			return "-";
		}
		if (text.contains("/")) {
			return "/";
		}
		throw new IllegalArgumentException("지원하지 않는 형식의 날짜입니다.");
	}

	private static int toInt(String string) {
		return Integer.parseInt(string.strip());
	}
}
