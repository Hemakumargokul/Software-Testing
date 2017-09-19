package projectVersion;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.ExpectedException;

public class Printtokens2Test {

	Printtokens2 b=new Printtokens2();
	int error = 0;
	int keyword = 1;
	int spec_symbol = 2;
	int identifier = 3;
	int num_constant = 41;
	int str_constant = 42;
	int char_constant = 43;
	int comment = 5;
	SecurityManager securityManager=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void open_character_stream() throws FileNotFoundException{
		BufferedReader br=null;
		br=b.open_character_stream(null);
		assertNotNull(br);
		br=b.open_character_stream("input.txt");
		assertNotNull(br);
		
		//thrown.expect(FileNotFoundException.class);	
		b.open_character_stream("something");
			
		
		
		//assertTrue( true, myReturnedObject instaceof Expression);
	}
	@Test
	public void open_token_stream() {
		BufferedReader br=null;
		br=b.open_token_stream("");
		assertNotNull(br);
		br=b.open_token_stream("input.txt");
		assertNotNull(br);
		
		
		
		//assertTrue( true, myReturnedObject instaceof Expression);
	}
	@Test
	public void is_token_end() {
		int id,res;
		//edge [1,2]
		id=1;		res=-1;
		assertTrue(b.is_token_end(id,res));
		
		//edge [1,3,4,5]
		id=1;		res=34;
		assertTrue(b.is_token_end(id,res));
		
		//edge [1,3,4,6]
		id=1;		res=65;
		assertEquals(false,b.is_token_end(id,res));
		
		//edge [1,3,7,8,9]
		id=2;		res=32;
		assertTrue(b.is_token_end(id,res));
		id=2;		res=10;
		assertTrue(b.is_token_end(id,res));
		id=2;		res=13;
		assertTrue(b.is_token_end(id,res));
		
		//edge [1,3,7,8,10]
		id=2;		res=65;
		assertEquals(false,b.is_token_end(id,res));
		
		//edge [1,3,7,11,12]
				id=0;		res=44;
				assertTrue(b.is_token_end(id,res));
				
		//edge [1,3,7,11,13,14]
				id=0;		res=32;
				assertTrue(b.is_token_end(id,res));	
				id=2;		res=10;
				assertTrue(b.is_token_end(id,res));
				id=2;		res=13;
				assertTrue(b.is_token_end(id,res));
		//edge [1,3,7,11,13,15]
				id=0;		res=65;
				assertEquals(false,b.is_token_end(id,res));	
		
		
		
		
		//assertTrue( true, myReturnedObject instaceof Expression);
	}
	@Test
	public void token_type() {
		
		
		//keyword
		assertEquals(keyword,b.token_type("or"));
		//spec_symbol
		assertEquals(spec_symbol,b.token_type(","));
		//identifier
		assertEquals(identifier,b.token_type("hello"));
		//num_constant
		assertEquals(num_constant,b.token_type("1"));
		//str_constant
		assertEquals(str_constant,b.token_type("\"a\""));
		//char_constant
		assertEquals(char_constant,b.token_type("#a"));
		//comment
		assertEquals(comment,b.token_type(";comment"));
		//error
		assertEquals(error,b.token_type("?"));		
	}
	@Test
	public void print_token() {
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
	      String tok=null;
	     //keyword
	      	tok="or";
			b.print_token(tok);
			assertEquals("keyword,\"" + tok + "\".\n",outContent.toString());
			//spec_symbol
			 outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok=",";
			b.print_token(tok);
			assertEquals("comma.\n",outContent.toString());
			
		      outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="(";
			b.print_token(tok);
			assertEquals("lparen.\n",outContent.toString());
			
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok=")";
			b.print_token(tok);
			assertEquals("rparen.\n",outContent.toString());
			
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="[";
			b.print_token(tok);
			assertEquals("lsquare.\n",outContent.toString());
			
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="]";
			b.print_token(tok);
			assertEquals("rsquare.\n",outContent.toString());
			
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="'";
			b.print_token(tok);
			assertEquals("quote.\n",outContent.toString());
			
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="`";
			b.print_token(tok);
			assertEquals("bquote.\n",outContent.toString());
			
		//identifier
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="hello";
			b.print_token(tok);
			assertEquals("identifier,\"" + tok + "\".\n",outContent.toString());
		//num_constant
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="1";
			b.print_token(tok);
			assertEquals("numeric," + tok + ".\n",outContent.toString());
		//str_constant
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="\"a\"";
			b.print_token(tok);
			assertEquals("string," + tok + ".\n",outContent.toString());
		//char_constant
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="#a";
			b.print_token(tok);
			assertEquals("character,\"" + tok.charAt(1) + "\".\n",outContent.toString());
		
		//error
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok="?";
			b.print_token(tok);
			assertEquals("error,\"" + tok + "\".\n",outContent.toString());		
			
			//comment
			outContent = new ByteArrayOutputStream();
		      System.setOut(new PrintStream(outContent));
			tok=";comment";
			b.print_token(tok);
			assertEquals("comment,\"" + tok.substring(1,tok.length()) + "\".\n",outContent.toString());			
	}
	@Test
	public void is_comment() {
		String ident=";";
		assertTrue(b.is_comment(ident));
		
		ident="?";
		assertFalse(b.is_comment(ident));
	}
	@Test
	public void is_keyword() {
		String ident="or";
		assertTrue(b.is_keyword(ident));
		
		ident="and";
		assertTrue(b.is_keyword(ident));
		
		ident="if";
		assertTrue(b.is_keyword(ident));
		
		ident="xor";
		assertTrue(b.is_keyword(ident));
		
		ident="lambda";
		assertTrue(b.is_keyword(ident));
		
		ident="=>";
		assertTrue(b.is_keyword(ident));
		
		ident="?";
		assertFalse(b.is_keyword(ident));
	}
	@Test
	public void is_char_constant() {
		String ident="?";
		assertFalse(b.is_char_constant(ident));
		ident="#a";
		assertTrue(b.is_char_constant(ident));
		
		
	}
	@Test
	public void is_num_constant() {
		String ident="a";
		assertFalse(b.is_num_constant(ident));
		ident="1a";
		assertFalse(b.is_num_constant(ident));
		ident="1";
		assertTrue(b.is_num_constant(ident));
		ident="12";
		assertTrue(b.is_num_constant(ident));
		
		
		
	}
	@Test
	public void is_str_constant() {
		String ident="1";
		assertFalse(b.is_str_constant(ident));
		
		ident="\"";
		assertTrue(b.is_str_constant(ident));
		ident="\"a\"";
		assertTrue(b.is_str_constant(ident));
		
		
		
	}
	@Test
	public void is_identifier_constant() {
		String ident="a?";
		assertFalse(b.is_identifier(ident));
		ident="1";
		assertFalse(b.is_identifier(ident));
		ident="a";
		assertTrue(b.is_identifier(ident));
		ident="hello";
		assertTrue(b.is_identifier(ident));
		
		
		
	}
	
	@Test
	public void print_spec_symbol() {
		//spec_symbol
		 ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
	      
		String tok=",";
		b.print_token(tok);
		assertEquals("comma.\n",outContent.toString());
		
	      outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok="(";
		b.print_token(tok);
		assertEquals("lparen.\n",outContent.toString());
		
		outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok=")";
		b.print_token(tok);
		assertEquals("rparen.\n",outContent.toString());
		
		outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok="[";
		b.print_token(tok);
		assertEquals("lsquare.\n",outContent.toString());
		
		outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok="]";
		b.print_token(tok);
		assertEquals("rsquare.\n",outContent.toString());
		
		outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok="'";
		b.print_token(tok);
		assertEquals("quote.\n",outContent.toString());
		
		outContent = new ByteArrayOutputStream();
	      System.setOut(new PrintStream(outContent));
		tok="`";
		b.print_token(tok);
		assertEquals("bquote.\n",outContent.toString());
	}
	
	@Test
	public void is_spec_symbol() {
		
		//spec_symbol
		 
		char tok=',';
		
		assertEquals(true,b.is_spec_symbol(tok));
		
	      
		tok='(';
		assertEquals(true,b.is_spec_symbol(tok));
		
		
		tok=')';
		assertEquals(true,b.is_spec_symbol(tok));
		
		
		tok='[';
		assertEquals(true,b.is_spec_symbol(tok));
		
		
		tok=']';
		assertEquals(true,b.is_spec_symbol(tok));
		
		
		tok='\'';
		assertEquals(true,b.is_spec_symbol(tok));
		
		tok='`';
		assertEquals(true,b.is_spec_symbol(tok));
		
	}
	@Test
	public void get_token() {
		BufferedReader br = null;
		StringBuilder b1=null;
		Reader inputString = null;
		
			inputString = new StringReader("hello");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("hello");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			//empty input
			inputString = new StringReader("");
			br = new BufferedReader(inputString);	
			b1=null;
			assertEquals(null,b.get_token(br));
			
			//other inputs
			inputString = new StringReader(" ");
			br = new BufferedReader(inputString);	
			b1=null;
			assertEquals(null,b.get_token(br));
			
			inputString = new StringReader("\n");
			br = new BufferedReader(inputString);	
			b1=null;
			assertEquals(null,b.get_token(br));
			
			inputString = new StringReader("\r");
			br = new BufferedReader(inputString);	
			b1=null;
			assertEquals(null,b.get_token(br));
			
			inputString = new StringReader(" a");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("a");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader("a");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("a");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader(",");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append(",");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader("\"a\"");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("\"a\"");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader(";comment");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append(";comment");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader("hello,");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("hello");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			inputString = new StringReader("hello;");
			br = new BufferedReader(inputString);	
			b1=new StringBuilder();
			b1.append("hello");
			assertEquals(b1.toString(),b.get_token(br).toString());
			
			
			
			
			
			
			
	}
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Test
	public void main() {
	String[] args={"a","b"};
	String[] args1={"input.txt"};
	String[] args2={};
	
	
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
	try {
		//error input
		//b.main(args);
		
		
		b.main(args1);
		
		
		
		assertEquals("identifier,\"hello\".\n",outContent.toString());
		
		
		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
	    System.setIn(in);
	    b.main(args2);
		
		
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    
	    exit.expectSystemExit();
	    b.main(args);
	    
	    
		//assertEquals("Error!,please give the token stream\n",outContent.toString());
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
