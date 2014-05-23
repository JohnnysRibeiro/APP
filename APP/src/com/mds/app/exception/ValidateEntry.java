/* File: ValidateEntry.java
 * 
 * Package: com.mds.app.exception
 * 
 * Description: This is an exception class that verifies and validate a lot of information entries.  
 *
 */

package com.mds.app.exception;

public abstract class ValidateEntry {

	public static boolean validateEntry(String data) {
		if (data.equals("")) {
			return true;
		}

		return data.matches("\\d{2}/\\d{2}/\\d{4}");
	}

	public static boolean validateAuthor(String author) {
		if (author.equals("")) {
			return true;
		}

		return author.matches("[a-zA-Z]*");
	}

	public static boolean validateNumber(String number) {
		if (number.equals("")) {
			return true;
		}

		return number.matches("\\d{4}");
	}

	public static boolean validateYear(String year) {
		return year.matches("\\d{4}");
	}

	public static boolean validateAcronym(String acronym) {
		return acronym.matches("[a-zA-Z]*");
	}

	public static boolean validateStateAbbreviation(String stateAbbreviation) {
		if (stateAbbreviation.equals("")) {
			return true;
		}
		return stateAbbreviation.matches("\\w{2}[a-zA-Z]*");

	}

	public static boolean[] validateEntries(String year, String acronym, String number, String initialDate,
			String author, String politicalParty, String stateAbbreviation) {
		boolean entries[] = { false, false, false, false, false, false, false };

		if (!validateYear(year)) {
			entries[0] = true;
		}
		if (!validateAcronym(acronym)) {
			entries[1] = true;
		}
		if (!validateNumber(number)) {
			entries[2] = true;
		}
		if (!validateEntry(initialDate)) {
			entries[3] = true;
		}
		if (!validateAuthor(author)) {
			entries[4] = true;
		}
		if (!validateAcronym(politicalParty)) {
			entries[5] = true;
		}
		if (!validateStateAbbreviation(stateAbbreviation)) {
			entries[6] = true;
		}
		return entries;
	}

	public static String ensurePoliticalPartyResult(String stateAbbreviation, String politicalParty) {
		if (!stateAbbreviation.isEmpty() && !politicalParty.isEmpty()) {
			return "";
		}
		else {
			return politicalParty;
		}
	}
	
	public static String ensureAuthorResult(String author, String politicalParty) {
		if (!author.isEmpty() && !politicalParty.isEmpty()) {
			return "";
		}
		else {
			return politicalParty;
		}
	}

	public static String discoverErrors(String year, String acronym, String number, String initialDate, String date,
			String politicalParty, String stateAbbreviation) {
		boolean receiveValidation[];

		receiveValidation = validateEntries(year, acronym, number, initialDate, date, politicalParty, stateAbbreviation);
		String errors = "";

		if (receiveValidation[0] == true) {
			errors += " Ano invalido ";
		}
		if (receiveValidation[1] == true) {
			errors += " Sigla Invalida ";
		}
		if (receiveValidation[2] == true) {
			errors += " Numero invalido ";
		}
		if (receiveValidation[3] == true) {
			errors += " Data inicial invalida ";
		}
		if (receiveValidation[4] == true) {
			errors += " Autor invalido ";
		}
		if (receiveValidation[5] == true) {
			errors += " Partido invalido ";
		}
		if (receiveValidation[6] == true) {
			errors += " UF invalida ";
		}

		return errors;
	}
}