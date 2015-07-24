package wuliuxitong;
/*
 * 实现此接口的Panel类（YunDanPanel HuoDanPanel）在reload内进行更新操作
 * 当YunDan HuoDan等进行修改或删除操作后，需要对相应的显示查找结果的界面（YunDanPanel HuoDanPanel）进行同步更新时
 * 只需传入实现了此接口的Panel，并调用reload方法即可
 * str为传入的字符串参数 可作为显示界面的查询条件
 */
public interface ReLoad {
	public void reload(String str);
}
