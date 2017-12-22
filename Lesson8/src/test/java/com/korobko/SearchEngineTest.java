package com.korobko;

import org.junit.*;
import org.mockito.Matchers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Unit test for simple SearchEngine.
 */
public class SearchEngineTest {
    private static String urlContent = "height=\"24\" class=\"user-info__image-pic user-info";
    private static URLStreamHandlerFactory urlStreamHandlerFactory;
    private static Map<String, Map<String, Integer>> resultUrlToWordsMap;
    private static Set<String> testSet;

    @BeforeClass
    public static void beforeClass() throws IOException {
        urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        URL.setURLStreamHandlerFactory(urlStreamHandlerFactory);
        initialize();
    }

    @AfterClass
    public static void afterClass() {
    }

    private static void initialize() throws IOException {

        AbstractPublicStreamHandler publicStreamHandler = mock(AbstractPublicStreamHandler.class);
        doReturn(publicStreamHandler).when(urlStreamHandlerFactory).createURLStreamHandler(Matchers.eq("http"));

        URLConnection mockedConnection = mock(URLConnection.class);
        doReturn(mockedConnection).when(publicStreamHandler).openConnection(Matchers.any(URL.class));

        doReturn(new ByteArrayInputStream(urlContent.getBytes("UTF-8"))).when(mockedConnection).getInputStream();

        testSet = new HashSet<>();
        testSet.add(URLServiceTest.HTTP_LOCALHOST);
        resultUrlToWordsMap = SearchEngine.getUrlToWordsMap(testSet);
    }
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findURLsByWordAppearance_givenWordAndMap_mapSortedAscending() {
        Map<String, Map<String, Integer>> testMap = new HashMap<>();
        String testWord = "image";
        for (int i = 3; i > 0; i--) {
            Map<String, Integer> innerMap = new HashMap<>();
            innerMap.put(testWord, i);
            testMap.put(URLServiceTest.HTTP_LOCALHOST+i, innerMap);
        }
        Map<Integer, String> resultMap = SearchEngine.findURLsByWordAppearance(testWord, testMap);
        Iterator<Integer> iterator = resultMap.keySet().iterator();
        for (int i = 1; i < resultMap.keySet().size(); i++) {
            assertTrue(i == iterator.next());
        }
    }

    @Test
    public void findURLsByWordAppearance_givenWordNotPresent_emptyMap() {
        Map<String, Map<String, Integer>> testMap = new HashMap<>();
        String testWord = "image";
        for (int i = 3; i > 0; i--) {
            Map<String, Integer> innerMap = new HashMap<>();
            innerMap.put(testWord, i);
            testMap.put(URLServiceTest.HTTP_LOCALHOST+i, innerMap);
        }
        Map<Integer, String> resultMap = SearchEngine.findURLsByWordAppearance("class", testMap);

        assertTrue(resultMap.isEmpty());

    }

    @Test
    public void getUrlToWordsMap_givenUrlAndContent_containsUrl() throws IOException {

        String expectedUrl = URLServiceTest.HTTP_LOCALHOST;
        assertTrue(resultUrlToWordsMap.containsKey(expectedUrl));

    }

    @Test
    public void getUrlToWordsMap_givenUrlAndContent_containsWordsAndFrequency() throws IOException {

        Map<String, Integer> actualMap = resultUrlToWordsMap.get(URLServiceTest.HTTP_LOCALHOST);

        assertTrue(actualMap.get("user") == 2);
        assertTrue(actualMap.get("info") == 2);

    }

    private abstract class AbstractPublicStreamHandler extends URLStreamHandler {
        @Override
        public URLConnection openConnection(URL url) throws IOException {
            return null;
        }
    }
}
