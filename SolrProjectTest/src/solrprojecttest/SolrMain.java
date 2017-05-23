package solrprojecttest;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;




public class SolrMain {
	
	//  private static AutoDetectParser autoParser;
	 private static String urlString = "http://localhost:8983/solr";
	 private	static SolrServer solr = new HttpSolrServer(urlString);
	 
	 private static File Dir;
	 
	public static  void main(String[] args) throws SolrServerException, IOException{
	Scanner input = new Scanner(System.in);
	
		System.out.println("Enter the directory containing files to be indexed");
		String dir = input.next();
		Dir = new File(dir);
		//indexing data to solr manually
	SolrInputDocument myDocument = new SolrInputDocument();
	myDocument.addField("id", "bug");
		myDocument.addField("constant", "8976.55");
		myDocument.addField("name", "The legend of not highlighting the content using eclipse");
		myDocument.addField("pdf","show this filed as it is a templets");
		myDocument.addField("Doc","Testing");
		
		solr.add(myDocument);
		
		
//		//indexing a pdf file 
//		// the id is specified in the constructor to provide the document with the composory field id which is unique by default
//		//Also setting the commit = true allows the document to be available for searching after doing indexing on the document
//		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract?literal.id=doc1&commit=true");
//		req.addFile(new File("/home/harisu/Index/hackingciphers.pdf"),"text");
//		req.setParam("EXTRACT_ONLY", "true");
//		NamedList<Object> resultReturned = solr.request(req);
//		System.out.println("Result: " + resultReturned);
//	     
		solrIndexPdf(Dir);
		
		solr.commit();
		//searching the indexed data from solr
		SolrQuery query = new SolrQuery();
		query.setQuery("pdf:templets");
		
		query.setFields("name","id","constant","pdf","Doc");
		query.setStart(0);
		QueryResponse response = solr.query(query);
		SolrDocumentList result = response.getResults();
		if(result.size()<0)
			System.out.println("no result found");
		for(int i=0;i<result.size();i++)
			System.out.println("Result  "+i+" is "+result.get(i));
		
		solrSearchPdfs();

			      } 
	
	//indexing a pdf file 
	//this method recursively moves through a directory and gets the files in that directory and index it no matter the depth of the directory
	//
	// the id is specified in the constructor to provide the document with the composory field id which is unique by default
	//Also setting the commit = true allows the document to be available for searching after doing indexing on the document
	//but we will only do a commit aftere all of our files have been index ( a batch commit)
	
	public static void solrIndexPdf(File rootDirOrFile) throws IOException, SolrServerException{
		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract");
		try{
			File files[]  = rootDirOrFile.listFiles();
				for(int i=0;i<files.length;i++){
					if(files[i].isDirectory())
						solrIndexPdf(files[i]);
					else{
						req.addFile(files[i],"text");
						req.setParam("EXTRACT_ONLY", "true");
						req.setParam("literal.id",files[i].getName());
						NamedList<Object> resultReturned = solr.request(req);
					}
				}
	
		}
		catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}
	
	
	public static void solrSearchPdfs() throws SolrServerException{
		SolrQuery queryDocs = new SolrQuery();
		queryDocs.setQuery("indexSearcher");
		
		queryDocs.setFields("name","id");
		queryDocs.setStart(0);
		QueryResponse responseDoc = solr.query(queryDocs);
		SolrDocumentList result = responseDoc.getResults();
		if(result.size()<0)
			System.out.println("no result found for the specified file");
		for(int i=0;i<result.size();i++)
			System.out.println("Result  "+i+" is "+result.get(i));

	}
	

}
