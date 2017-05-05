package Demo;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class Test {
	public static IndexWriter  writer;
	public static String file ="/home/harisu/fanyui";
	public static	File indexDir =new File("/home/harisu/Index");
 	
 	
	public static void main(String[] args) throws IOException, ParseException, SAXException, TikaException{
		
		 	
		 	
		 	
		 	
		 	
		      Directory indexDirectory =  FSDirectory.open(Paths.get(file));
		      StandardAnalyzer analyser = new StandardAnalyzer();
		      IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyser);
		      indexWriterConfig.setOpenMode(OpenMode.CREATE);
		      
		       writer = new IndexWriter(indexDirectory,indexWriterConfig);
		     // System.out.println("Indexing "+new File(file).getCanonicalPath());
		      ArrayList<String> files = getFileNames(indexDir);
		      //see the content of the files directory
		      for(String s:files)
		    	  System.out.println(s);
		      
		      for(String s:files){
		    	  File file = new File(s);
		    	  
		    	  if(file.getName().endsWith(".txt")){
		    		  addTextFiles(file);
		    	  }
		    	  else if(file.getName().endsWith(".pdf")){
		    		 addPdfFiles(file);
		    	  }
			      
		      }
		      
		      
		     
		      //index file name
//		      Field fileNameField = new StringField(Constants.FILE_NAME,file.getName(),Field.Store.YES);
//		      Field filePathField = new StringField(Constants.FILE_PATH,file.getCanonicalPath(),Field.Store.YES);

		      
//		      document.add(fileNameField);
//		      document.add(filePathField);;
		  
		      writer.close();
		      System.out.println("Indexed sucessfull");
		      		      
		      //searcher
		      IndexReader indexDirReader = DirectoryReader.open(FSDirectory.open(Paths.get(file) ));
		      IndexSearcher indexSearcher = new IndexSearcher(indexDirReader);
		      QueryParser qParser = new QueryParser(Constants.CONTENTS,new StandardAnalyzer());
		      Query query = qParser.parse("hacking");
		     
		  
		    		  
		      
		      
		      TopDocs hits = indexSearcher.search(query, Constants.MAX_SEARCH);
		      
		      System.out.println(hits.totalHits+" hits");
		    	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
		    	    	Document doc =  indexSearcher.doc(scoreDoc.doc);
		    	    	  
		    	         //Document doc = searcher.getDocument(scoreDoc);
		    	            System.out.println("File: " + doc.get(Constants.FILE_NAME));
		    	            System.out.println("path: "+doc.get(Constants.FILE_PATH));
		    	      }
		    	     	
		   
	}
	

	public static ArrayList<String> getFileNames(File file){
		ArrayList<String> names= new ArrayList<>();
		try{
			
			File[] result = file.listFiles();
			for(int i=0;i<result.length;i++){
				names.add(result[i].getCanonicalPath());
				
			}
			
			
		}
		catch(NullPointerException ex){
			ex.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return names;
	}
	
	//add pdf files to the document
	public static void addPdfFiles(File file) throws IOException, SAXException, TikaException{
		BodyContentHandler handler = new BodyContentHandler(-1);
	      Metadata metadata = new Metadata();
	      FileInputStream input = new FileInputStream(file);
	      ParseContext pcontext = new ParseContext();
	      
	      //parsing the document using PDF parser
	      PDFParser pdfParser = new PDFParser(); 
	      pdfParser.parse(input, handler, metadata,pcontext);
	      
	      //getting the content of the document
	      String pdfContent =   handler.toString();
	      File pdf = new File("home");
	      PrintWriter out = new PrintWriter(pdf);
	      out.print(pdfContent);
	      out.close();
	      //System.out.println(pdfContent);
	      Document document = new Document();
	   	 
	  	  Field contentField = new TextField(Constants.CONTENTS,new FileReader(pdf));
		      Field authorField = new StringField(Constants.AUTHOR,"harisu",Field.Store.YES);
		      
		      Field fileNameField = new StringField(Constants.FILE_NAME,"PDF",Field.Store.YES);
		      Field filePathField = new StringField(Constants.FILE_PATH,"pdfPath",Field.Store.YES);
		      //Field contentField = new StringField(Constants.CONTENTS,pdfContent,Field.Store.NO);

		      
		      document.add(contentField);
		      document.add(authorField);
		      document.add(fileNameField);
		      document.add(filePathField);
		      writer.addDocument(document);
	}
	public static void addTextFiles(File s) throws IOException{
		Document document = new Document();
  	 
  	  Field contentField = new TextField(Constants.CONTENTS,new FileReader(s));
	      Field authorField = new StringField(Constants.AUTHOR,"harisu fanyui",Field.Store.YES);
	      
	      Field fileNameField = new StringField(Constants.FILE_NAME,s.getName(),Field.Store.YES);
	      Field filePathField = new StringField(Constants.FILE_PATH,s.getCanonicalPath(),Field.Store.YES);

	      
	      document.add(contentField);
	      document.add(authorField);
	      document.add(fileNameField);
	      document.add(filePathField);
	      writer.addDocument(document);
	}

}




//String indexDir = "/home/harisu/Index";
//String dataDir = "/home/harisu/Data";
//Indexer indexer;
//Searcher searcher;
//
//public static void main(String[] args) {
//	      Test tester;
//	      try {
//	         tester = new Test();
//	         tester.createIndex();
//	         tester.search("Mohan");
//	      } catch (IOException e) {
//	         e.printStackTrace();
//	      } catch (ParseException e) {
//	         e.printStackTrace();
//	      }
//	   }
//
//private void createIndex() throws IOException {
//	      indexer = new Indexer(indexDir);
//	      int numIndexed;
//	      long startTime = System.currentTimeMillis();	
//	      numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
//	      long endTime = System.currentTimeMillis();
//	      indexer.close();
//	      System.out.println(numIndexed+" File indexed, time taken: "
//	         +(endTime-startTime)+" ms");		
//	   }
//
//private void search(String searchQuery) throws IOException, ParseException {
//	      searcher = new Searcher(indexDir);
//	      long startTime = System.currentTimeMillis();
//	      TopDocs hits = searcher.search(searchQuery);
//	      long endTime = System.currentTimeMillis();
//	   
//	      System.out.println(hits.totalHits +
//	         " documents found. Time :" + (endTime - startTime));
//	      for(ScoreDoc scoreDoc : hits.scoreDocs) {
//	         Document doc = searcher.getDocument(scoreDoc);
//	            System.out.println("File: "+ doc.get(Constants.FILE_PATH));
//	      }
//	      searcher.close();
//	   }