package application;

import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.web.WebView;

// Got the 
public class Print implements EventHandler<ActionEvent> {

	WebView webView;

	public Print(WebView webView) {
		this.webView = webView;
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		PrinterJob job;
		ObservableSet<Printer> printers = Printer.getAllPrinters();
		int i = 0;
		for (Printer each : printers) {
			if (each.getName().equals("Microsoft Print to PDF")) {

				// Got this next block from the javadoc for PrinterJob
				job = PrinterJob.createPrinterJob(each);
				i++;
				if (job != null) {
					boolean success = job.printPage(webView);
					if (success) {
						job.endJob();
					}
				}
				break;
			}
		}
		if (i == 0) {
			System.out.println("Printer not found");
		}
	}

}
