import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


public class testParse {
	private static HTMLParser cut;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cut = new HTMLParser();
	}
	@Test
	public void testSimpleParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		testHTMLTree.getChild().add(new HTMLTree("<html>"));
		cut.parse(retHTMLTree, "<HTML></html>");
		assertEquals("Parse <HTML></html> : ", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testEmptyParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		cut.parse(retHTMLTree, "");
		assertEquals("Parse empty :", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testNULLStringParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		cut.parse(retHTMLTree,null);
		assertEquals("Parse Null-String :", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testNoTagParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		testHTMLTree.getChild().add(new HTMLTree("Hallo"));
		cut.parse(retHTMLTree, "Hallo");
		assertEquals("Parse Text :", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testMultiTagDropScriptParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		testHTMLTree.getChild().add(new HTMLTree("<html>"));
		cut.parse(retHTMLTree, "<html><script lanuage='sprache'>Bla Bla</script></html>");
		assertEquals("Test", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testMultiTagDropCommentParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		testHTMLTree.getChild().add(new HTMLTree("<html>"));
		cut.parse(retHTMLTree, "<html><!-- Comment --></html>");
		assertEquals("Test", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testMultiTagDropHeadParse() {
		HTMLTree testHTMLTree,retHTMLTree;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		testHTMLTree.getChild().add(new HTMLTree("<html>"));
		cut.parse(retHTMLTree, "<html><Head>Header</head></html>");
		assertEquals("Test", testHTMLTree,retHTMLTree);
	}
	@Test
	public void testMultiTagParse() {
		HTMLTree testHTMLTree,retHTMLTree,tTree=null;
		testHTMLTree = new HTMLTree("");
		retHTMLTree = new HTMLTree("");
		tTree= new HTMLTree("<html>");
		testHTMLTree.getChild().add(tTree);
		tTree.getChild().add(new HTMLTree("<body>"));
		cut.parse(retHTMLTree, "<html><body> </body></html>");
		assertEquals("<html><body></body></html>", testHTMLTree,retHTMLTree);
	}
}
