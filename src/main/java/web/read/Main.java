package web.read;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Main {

	public static void mains(String[] args) {
		Zwangsversteigerungen zw=new Zwangsversteigerungen();
		zw.setZwangsversteigerungID("beschr ");
		Adresse address = new Adresse();

		address.setCity("zwei").setCountry("dd").setProvince("jk"). setPostcode("1000").setStreet("Poribagh");
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		em.getTransaction().begin();
		 Zwangsversteigerungen find = em.find(Zwangsversteigerungen.class, 0L);
		if(find==null)
			System.out.println("nicht gefunden ------------");
		
		 em.persist(address);
 		em.persist(zw);
 		 em.persist(new Zwangsversteigerungen(new Date(),"hii"));
		em.getTransaction().commit();
		em.close();
		PersistenceManager.INSTANCE.close();
	}

	public static void main(String[] args) throws Exception {
		EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
		
			  
		// Function<String, Integer> intToString = str->Integer.parseInt(str);
		// Function<String, Integer> intToString2copy = Integer::parseInt;
		// Integer apply = intToString.apply("test");



		/*
		 * crawlStorageFolder is a folder where intermediate crawl data is
		 * stored.
		 */
		String crawlStorageFolder = "D:/tmp/webcrawler";

		/*
		 * numberOfCrawlers shows the number of concurrent threads that should
		 * be initiated for crawling.
		 */
		//int numberOfCrawlers = Integer.parseInt(args[1]);

		CrawlConfig config = new CrawlConfig();

 	config.setCrawlStorageFolder(crawlStorageFolder);

		/*
		 * Be polite: Make sure that we don't send more than 1 request per
		 * second (1000 milliseconds between requests).
		 */
		config.setPolitenessDelay(1000);

		/*
		 * You can set the maximum crawl depth here. The default value is -1 for
		 * unlimited depth
		 */
		config.setMaxDepthOfCrawling(1);

		/*
		 * You can set the maximum number of pages to crawl. The default value
		 * is -1 for unlimited number of pages
		 */
		config.setMaxPagesToFetch(-1);

		/*
		 * Do you need to set a proxy? If so, you can use:
		 * config.setProxyHost("proxyserver.example.com");
		 * config.setProxyPort(8080);
		 * 
		 * If your proxy also needs authentication:
		 * config.setProxyUsername(username); config.getProxyPassword(password);
		 */

		/*
		 * This config parameter can be used to set your crawl to be resumable
		 * (meaning that you can resume the crawl from a previously
		 * interrupted/crashed crawl). Note: if you enable resuming feature and
		 * want to start a fresh crawl, you need to delete the contents of
		 * rootFolder manually.
		 */
		config.setResumableCrawling(false);
		config.setCleanupDelaySeconds(1);
		config.setThreadShutdownDelaySeconds(0);

		/*
		 * Instantiate the controller for this crawl.
		 */
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */

		// controller.addSeed("https://zvsachsen.de/?azFullText=&_submit=1");
		controller.addSeed("https://zvsachsen.de/suche/immobilien?azFullText=&page=1");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
		controller.start(BasicCrawler.class, 1);
		System.out.println("fertig");
		TypedQuery<Zwangsversteigerungen> query =
			      em.createQuery("SELECT v FROM Zwangsversteigerungen v", Zwangsversteigerungen.class);
			  List<Zwangsversteigerungen> results = query.getResultList().stream().collect(Collectors.toList());
			  List<Zwangsversteigerungen> collect = BasicCrawler.zwnumber.stream().filter(
					  a->!results.contains(a)
					  ).collect(Collectors.toList());
			  System.out.println("Neuen Versteigerungen "+ collect.size());
		em.getTransaction().begin();

		collect.forEach(a->{


			em.persist(a);

		});
		em.getTransaction().commit();
		 
	}
}
