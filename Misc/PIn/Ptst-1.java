code to implement a load tester for a high-performant key-value store.


Interface HttpOp{
    public boolean execute(TestCase tc);
}

class TestCase{
    String url;
    String key;
    String Val;
    HttpOp op;
    
    public TestCase(String url, String key, String val, HttpOp op){
        assert op;
        assert url;
        this.url = url;
        this.key = key;
        this.eVal = val;
        this.op = op;
    }
}

class TestResult implement Comparable{
    TestCase tc;
    String actVal;
    Timestamp start;
    int resTime;

    public TestResult(TestCase tc){
        assert tc;
        this.tc = tc;
    }
    
    public compareTo(TestCase that){
        if(this.start == null || that.start == null) return 0;
        this.start.compareTo(that.start);
    }
}

class HttpGet implements HttpOP{
    private String getUrl(Test tc){
        return tc.url + "?key=" + tc.key;
    }
    
    protected String parser(String str){
        // return reponse body in certain format.
    }
    
    protected String getResponse(HttpURLConnection con){
        try{
            Bufferedreader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            int resCode = con.getResponseCode();
            if(resCod != HTTP_OK) return -1;
            String line; 
            StringBuffer res = new StringBuffer(); 
            while((line = in.readLine()) != null){
                res.append(inputLine);
            }
        }
        catch(SocketTimeoutException e){
            return null;
        }
        finally{
            in.close();
        }
    }
    
    protected setParam(HttpURLConnection con){
        con.setConnectTimeout(TIMEOUT_VALUE);
        con.setReadTimeout(TIMEOUT_VALUE);
        con.useCache(false);
    }

    public boolean execute(TestCase tc){
        String url = getUrl(tc); 
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        setParam(con);
        con.setRequestMethod("GET");
        
        long start = System.currentTimeMillis();
        tc.start = new TimeStart(start);
        String res = getResponse(con);
        tc.resTime = res == null? -1 : System.currentTimeMillis() - start;
        con.disconnect();
        
        tc.actVal = parser(res);      
        return tc.resTime != -1;
    }   
}

class HttpPost extends HttpGet{
    private String genHttpBody(tc){
        //
    }
    
    private boolean postContent(HttpURLConnection con, String content){
        try{
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters); 
            wr.flush();
            return true;
        }
        catch(Exception e){return false;}
        finally{
            wr.close();
        }
    }
    
    
    //overwrite
    public boolean execute(TestCase tc){
        HttpURLConnection con = (HttpURLConnection) new URL(tc.url).openConnection();
        setParam(con);
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        
        long start = System.currentTimeMillis();
        tc.start = new TimeStart(start);
        String res = null;
        if(postContent(con, genHttpBody(tc))){
            res = getResponse(con);
        }
        
        tc.resTime = res == null? -1 : System.currentTimeMillis() - start;
        con.disconnect();
        
        tc.actVal = parser(res);         
        return tc.resTime != -1;
    }    
}

class Producer implements Runnable {
    private final BlockingQueue queue;
    
    Producer(BlockingQueue q) { queue = q; }
    
    public void run() {
        try {
            while (true) { queue.put(produce()); }
        } catch (InterruptedException ex) { ... handle ...}
    }
    Object produce() { ... }
}

class Consumer implements Runnable {
    private final BlockingQueue queue;
    
    Consumer(BlockingQueue q) { queue = q; }
    
    public void run() {
        try {
            while (true) { consume(queue.take()); }
        } catch (InterruptedException ex) { ... handle ...}
    }
    
    void consume(Object x) { ... }
}

class Setup {
    void main() {
     BlockingQueue q = new SomeQueueImplementation();
     Producer p = new Producer(q);
     Consumer c1 = new Consumer(q);
     Consumer c2 = new Consumer(q);
     new Thread(p).start();
     new Thread(c1).start();
     new Thread(c2).start();
    }
}
