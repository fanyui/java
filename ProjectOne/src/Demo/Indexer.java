package Demo;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	
	private IndexWriter writer;

	   public Indexer(String indexDirectoryPath) throws IOException {
	      //this directory will contain the indexes
		   Path indexDir = Paths.get(indexDirectoryPath);
	      Directory indexDirectory =  FSDirectory.open(indexDir);
	      StandardAnalyzer analyser = new StandardAnalyzer();
	      IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyser);
	      indexWriterConfig.setOpenMode(OpenMode.CREATE);
	      
	       writer = new IndexWriter(indexDirectory,indexWriterConfig);
	      
	      
	

	   }
	   
	   public void close() throws CorruptIndexException, IOException {
		      writer.close();
		   }
	   
	   private Document getDocument(File file) throws IOException {
		   // Constants constant = new Constants();
		      Document document = new Document();

		      //index file contents
		      Field contentField = new Field(Constants.CONTENTS, new FileReader(file), null);
		      //index file name
		      Field fileNameField = new Field(Constants.FILE_NAME,file.getName(),null);
		      //index file path
		      Field filePathField = new Field(Constants.FILE_PATH,file.getCanonicalPath(),null);

		      document.add(contentField);
		      document.add(fileNameField);
		      document.add(filePathField);

		      return document;
		   } 
	   private void indexFile(File file) throws IOException {
		      System.out.println("Indexing "+file.getCanonicalPath());
		      Document document = getDocument(file);
		      writer.addDocument(document);
		   }
	   
	   public int createIndex(String dataDirPath, FileFilter filter) 
			      throws IOException {
			      //get all files in the data directory
			      File[] files = new File(dataDirPath).listFiles();

			      for (File file : files) {
			         if(!file.isDirectory()
			            && !file.isHidden()
			            && file.exists()
			            && file.canRead()
			            && filter.accept(file)
			         ){
			            indexFile(file);
			         }
			      }
			      return writer.numDocs();
			   }

	
	
}
