/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2017/12/29 下午3:39
 * @Description :
 */
public class Message {
    private int id;
    private String content;
    private long time;

    public Message(int id, String content, long time) {
        this.id = id;
        this.content = content;
        this.time = time;
    }

    public static Message handInfo(String info){
        int index0 = info.indexOf('#');
        int index1 = info.indexOf('-');
        int id = Integer.parseInt(info.substring(0,index0));
        String content = info.substring(index0+1,index1);
        long time = Long.parseLong(info.substring(index1+1,info.length()));
        Message message = new Message(id,content,time);
        return message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
