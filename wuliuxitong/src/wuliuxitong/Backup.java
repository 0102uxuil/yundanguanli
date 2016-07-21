package wuliuxitong;

public class Backup {
    private String user_name;//���ݿ��û���
    private String user_psw;//���ݿ�����
    private String db_name;// ��Ҫ���ݵ����ݿ���
    private String host_ip;
    private String user_charset;
    private String backup_path; //��ű����ļ���·��
    private String stmt;
    public Backup(String user_name,String user_psw,String db_name,String host_ip,String user_charset,String backup_path){
        this.user_name=user_name;
        this.user_psw=user_psw;
        this.db_name=db_name;
        //����IP;
        if(host_ip==null||host_ip.equals(""))
            this.host_ip="localhost";//Ĭ��Ϊ����
        else
            this.host_ip=host_ip;
        //�ַ���
        if(user_charset==null||user_charset.equals(""))
            this.user_charset=" "; //Ĭ��Ϊ��װʱ���õ��ַ���
        else
            this.user_charset=" --default-character-set="+user_charset;
        this.backup_path=backup_path;
//        this.stmt="d:\\MYSQL\\bin\\mysqldump "+this.db_name+" -h "+this.host_ip+" -u"+this.user_name+" -p"+this.user_psw
//                +this.user_charset+" --result-file="+this.backup_path;
        this.stmt="C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump "+this.db_name+" -h "+this.host_ip+" -u"+this.user_name+" -p"+this.user_psw
                +this.user_charset+" --result-file="+this.backup_path;
    }
    
    @SuppressWarnings("finally")
	public boolean backup_run(){
        boolean run_result=false;
        try{
            Runtime.getRuntime().exec(this.stmt);
            run_result=true;
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            return run_result;
        }
    }
}
