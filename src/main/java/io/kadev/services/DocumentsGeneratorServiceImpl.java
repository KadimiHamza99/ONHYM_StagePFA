package io.kadev.services;

import java.io.FileOutputStream;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import io.kadev.models.FormInformationSI;
import io.kadev.models.FormInformationsAM;

@Service
@Transactional
public class DocumentsGeneratorServiceImpl implements DocumentsGeneratorService {

	@Override
	public void fillDemandeAccesMessagerie(FormInformationsAM formInfo, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("DemandeAccesMobile.pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("./demandes/" + idDemande + ".pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(280, 671);
			pageContentByte.showText(formInfo.getNom() + " " + formInfo.getPrenom());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(170, 651);
			pageContentByte.showText(formInfo.getMatricule());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(400, 651);
			pageContentByte.showText(formInfo.getAffectation());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(280, 631);
			pageContentByte.showText(formInfo.getReferenceMobile());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(240, 611);
			pageContentByte.showText(formInfo.getNumeroSerieMobile());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(440, 611);
			pageContentByte.showText(formInfo.getImei());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 7);
			pageContentByte.setTextMatrix(450, 269);
			pageContentByte.showText(LocalDate.now().toString() + " " + formInfo.getNom() + " " + formInfo.getPrenom());
			pageContentByte.endText();

			pdfStamper.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void fillDemandeServiceSI(FormInformationSI formInfo, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("DemandeServiceSI.pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("./demandes/" + idDemande + ".pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);
			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(280, 641);
			pageContentByte.showText(formInfo.getNom() + " " + formInfo.getPrenom());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(250, 621);
			pageContentByte.showText(formInfo.getSociete());
			pageContentByte.endText();

			switch (formInfo.getServiceDemande()) {
			case "Compte de domaine":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 551);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Boite de messagerie":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(214, 551);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Connexion internet (adsl)":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(356, 551);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Acces au reseau local":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 531);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Materiel informatique":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(214, 531);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Telephonie interne":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(356, 531);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Acces a la messagerie via mobile":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 511);
				pageContentByte.showText("x");
				pageContentByte.endText();
				break;
			case "Application de gestion":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 492);
				pageContentByte.showText("x");
				pageContentByte.endText();

				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(350, 495);
				pageContentByte.showText(formInfo.getPrecisionServiceDemande());
				pageContentByte.endText();
				break;
			case "Application metier":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 467);
				pageContentByte.showText("x");
				pageContentByte.endText();

				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(350, 470);
				pageContentByte.showText(formInfo.getPrecisionServiceDemande());
				pageContentByte.endText();
				break;
			case "Autres":
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(73, 441);
				pageContentByte.showText("x");
				pageContentByte.endText();
				pageContentByte.beginText();
				pageContentByte.setFontAndSize(baseFont, 10);
				pageContentByte.setTextMatrix(350, 443);
				pageContentByte.showText(formInfo.getPrecisionServiceDemande());
				pageContentByte.endText();
				break;
			default:
				System.out.println("Choix incorrect");
				break;
			}

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 10);
			pageContentByte.setTextMatrix(300, 601);
			pageContentByte.showText(formInfo.getCadrePartenariat());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 7);
			pageContentByte.setTextMatrix(420, 277);
			pageContentByte.showText(LocalDate.now().toString() + " " + formInfo.getNom() + " " + formInfo.getPrenom());
			pageContentByte.endText();

			pdfStamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillValidationManagerAM(String nom, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("./demandes/" + idDemande + ".pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader,
					new FileOutputStream("./demandes/" + idDemande + "ManagerValidation.pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(455, 180);
			pageContentByte.showText(LocalDate.now().toString());
			pageContentByte.endText();

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(300, 180);
			pageContentByte.showText(nom);
			pageContentByte.endText();

			pdfStamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillValidationDsiAM(String nom, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("./demandes/" + idDemande + "Managervalidation.pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader,
					new FileOutputStream("./demandes/" + idDemande + "DsiValidation.pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(455, 130);
			pageContentByte.showText(LocalDate.now().toString());
			pageContentByte.endText();
			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(300, 130);
			pageContentByte.showText(nom);
			pageContentByte.endText();
			pdfStamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillValidationManagerSI(String nom, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("./demandes/" + idDemande + ".pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader,
					new FileOutputStream("./demandes/" + idDemande + "ManagerValidation.pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(455, 180);
			pageContentByte.showText(LocalDate.now().toString());
			pageContentByte.endText();
			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(320, 180);
			pageContentByte.showText(nom);
			pageContentByte.endText();
			pageContentByte.endText();
			pdfStamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillValidationDsiSI(String nom, String idDemande) {
		try {
			PdfReader pdfReader = new PdfReader("./demandes/" + idDemande + "ManagerValidation.pdf");
			PdfStamper pdfStamper = new PdfStamper(pdfReader,
					new FileOutputStream("./demandes/" + idDemande + "DsiValidation.pdf"));
			BaseFont baseFont = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			PdfContentByte pageContentByte = pdfStamper.getOverContent(1);

			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(455, 130);
			pageContentByte.showText(LocalDate.now().toString());
			pageContentByte.endText();
			pageContentByte.beginText();
			pageContentByte.setFontAndSize(baseFont, 8);
			pageContentByte.setTextMatrix(320, 130);
			pageContentByte.showText(nom);
			pageContentByte.endText();
			pdfStamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
