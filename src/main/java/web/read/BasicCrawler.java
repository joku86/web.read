package web.read;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class BasicCrawler extends WebCrawler {
	static List<Zwangsversteigerungen> zwnumber=new ArrayList<>();

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	public boolean shouldVisit(Page page,WebURL url) {
		String href = url.getURL().toLowerCase();
		return !FILTERS.matcher(href).matches() && href.toLowerCase().startsWith("https://zvsachsen.de/suche/immobilien?azfulltext");
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		System.out.println(page.getWebURL().getURL());
//		int docid = page.getWebURL().getDocid();
//		String url = page.getWebURL().getURL();
//		String domain = page.getWebURL().getDomain();
//		String path = page.getWebURL().getPath();
//		String subDomain = page.getWebURL().getSubDomain();
//		String parentUrl = page.getWebURL().getParentUrl();
//		String anchor = page.getWebURL().getAnchor();
//
//		System.out.println("Docid: " + docid);
//		System.out.println("URL: " + url);
//		System.out.println("Domain: '" + domain + "'");
//		System.out.println("Sub-domain: '" + subDomain + "'");
//		System.out.println("Path: '" + path + "'");
//		System.out.println("Parent page: " + parentUrl);
//		System.out.println("Anchor text: " + anchor);
		
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			Set<WebURL> links = htmlParseData.getOutgoingUrls();


			Document doc = Jsoup.parse(html);
			Elements newsHeadlines = doc.select(".immoBox");
			//Elements select = newsHeadlines.select("h3");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm 'Uhr'", Locale.GERMANY);
			newsHeadlines.forEach(e-> {System.out.println(e.text());
				Zwangsversteigerungen zv = new Zwangsversteigerungen();
				zv.setDatum(new Date());
				zv.setZwangsversteigerungID(e.select("h3").get(0).text());
				zv.setAktenzeichen(e.select(".objektNummer").get(0).text());
				LocalDateTime parse = LocalDateTime.parse(e.select(".zvDate").get(0).text(), formatter);
				zv.setTermin(parse);
				zv.setAdresse(e.select(".strasse").get(0).text() );
				zwnumber.add(zv);
				//e.getAllElements().forEach(System.out::println);
			});

		}

//		Header[] responseHeaders = page.getFetchResponseHeaders();
//		if (responseHeaders != null) {
//			System.out.println("Response headers:");
//			for (Header header : responseHeaders) {
//				System.out.println("\t" + header.getName() + ": " + header.getValue());
//			}
//		}
		
		//System.out.println("=============");
	}
}