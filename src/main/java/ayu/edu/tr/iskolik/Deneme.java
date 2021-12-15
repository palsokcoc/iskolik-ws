package ayu.edu.tr.iskolik;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deneme {
	public static void main(String[] args) {
		long sayi = 12242324;
		System.out.println(java.text.NumberFormat.getNumberInstance(new java.util.Locale("TR-tr")).format(sayi));
		System.out.println(java.time.format.DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now()));

		LocalDate ilkTarih = LocalDate.EPOCH;
		LocalDate sonTarih = LocalDate.now();
		String baslikTarihi;
		if(ilkTarih == null && sonTarih == null) {
			baslikTarihi = "Tüm Zamanlar";
		} else if (ilkTarih != null && sonTarih == null) {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + " Tarihinden Sonrası";
		} else if (ilkTarih == null && sonTarih != null) {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + " Tarihine Kadar";
		} else {
			baslikTarihi = DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(ilkTarih) + " - " + DateTimeFormatter.ofLocalizedDate(java.time.format.FormatStyle.LONG).format(sonTarih);
		}
		System.out.println(baslikTarihi);
//		return baslikTarihi;
	}
}
