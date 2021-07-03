package Model;

import java.time.LocalDate;

public class MyDate {
	private int day;
	private int month;
	private int year;

	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public MyDate(int year) {
		this(1, 1, year);
	}

	public MyDate(int month, int year) {
		this(1, month, year);
	}

	public MyDate() {
		this(1, 1, 2020);
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String toStringAll() {
		return day + "/" + month + "/" + year;
	}

	public String toString() {
		return month + "/" + year;
	}
}
