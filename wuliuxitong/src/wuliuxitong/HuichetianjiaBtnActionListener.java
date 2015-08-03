package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

public class HuichetianjiaBtnActionListener implements ActionListener {
	
	YunDan yundan;
	
	HuichetianjiaBtnActionListener(YunDan yd){
		this.yundan = yd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int nullflag ,flag;
		nullflag = 0;
		flag = 0;
		if(this.yundan.huichehuowubianhao.getText().trim().equals("") || this.yundan.huichehuowubianhao.getText().trim() == null){
			nullflag = 1;
		} else {
			for(int i = 0; i < this.yundan.huichehuodanTM.getRowCount(); i++){
//				System.out.println(this.yundan.huichehuodanTM.getValueAt(i, 0));
				String str = "1" + this.yundan.huichehuowubianhao.getText().trim();
				if(str.equals(this.yundan.huichehuodanTM.getValueAt(i, 0))){
					flag = 1;
				}
			}
		}
		if(nullflag == 1){
			JOptionPane.showMessageDialog(null, "货物编号不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(flag == 1){
			JOptionPane.showMessageDialog(null, "货物编号不能重复！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.huichehuoming.getText().trim().equals("") || this.yundan.huichehuoming.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "货名不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.huichezhongliang.getText().trim().equals("") || this.yundan.huichezhongliang.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "重量不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.huichehuozhu.getText().trim().equals("") || this.yundan.huichehuozhu.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "货主不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(((this.yundan.huichejiage.getText().trim().equals("") || this.yundan.huichejiage.getText().trim() == null))
				&& ((this.yundan.huichebaodijia.getText().trim().equals("") || this.yundan.huichebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "价格和保底价不能全为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!((this.yundan.huichejiage.getText().trim().equals("") || this.yundan.huichejiage.getText().trim() == null))
				&& !((this.yundan.huichebaodijia.getText().trim().equals("") || this.yundan.huichebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "价格和保底价只能有一个！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huichezhongliang.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			System.out.println(this.yundan.huichezhongliang.getText().trim());
			JOptionPane.showMessageDialog(null, "重量必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huichezhongliang2.getText().trim().equals("") && this.yundan.huichezhongliang2.getText().trim() != null && !this.yundan.huichezhongliang2.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "重量2必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huichejiage.getText().trim().equals("") && this.yundan.huichejiage.getText().trim() != null && !this.yundan.huichejiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "单价必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huichebaodijia.getText().trim().equals("") && this.yundan.huichebaodijia.getText().trim() != null && !this.yundan.huichebaodijia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "保底价必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huicheqitafeiyong.getText().trim().equals("") && this.yundan.huicheqitafeiyong.getText().trim() != null && !this.yundan.huicheqitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "其他费用必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.huicheshifujine.getText().trim().equals("") && this.yundan.huicheshifujine.getText().trim() != null && !this.yundan.huicheshifujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "实付金额必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			Vector rec_vector = new Vector();
			rec_vector.addElement("1"+this.yundan.huichehuowubianhao.getText().trim());
			rec_vector.addElement(this.yundan.huichehuoming.getText().trim());
			rec_vector.addElement(this.yundan.huichezhongliang.getText().trim());
			rec_vector.addElement(this.yundan.huichezhongliang2.getText().trim());
			rec_vector.addElement(this.yundan.huichejiage.getText().trim());
			rec_vector.addElement(this.yundan.huichebaodijia.getText().trim());
//			rec_vector.addElement(this.yundan.huichezhesun.getText().trim());
			rec_vector.addElement(this.yundan.huichehuozhu.getText().trim());
			rec_vector.addElement(this.yundan.huicheqitafeiyong.getText().trim());
			rec_vector.addElement(this.yundan.huichebeizhu.getText().trim());
			float jine;
			if(this.yundan.huichebaodijia.getText().trim().equals("") || this.yundan.huichebaodijia.getText().trim() == null){
				jine = Float.parseFloat(this.yundan.huichezhongliang.getText().trim())*Float.parseFloat(this.yundan.huichejiage.getText().trim());
			} else {
				jine = Float.parseFloat(this.yundan.huichebaodijia.getText().trim());
			}
//			float huichezhesun, huicheqitafeiyong;
//			if(this.yundan.huichezhesun.getText().trim().equals("") || this.yundan.huichezhesun.getText().trim() == null){
//				huichezhesun = 0;
//			} else {
//				huichezhesun = Float.parseFloat(this.yundan.huichezhesun.getText().trim());
//			}
			float huicheqitafeiyong;
			if(this.yundan.huicheqitafeiyong.getText().trim().equals("") || this.yundan.huicheqitafeiyong.getText().trim() == null){
				huicheqitafeiyong = 0;
			} else {
				huicheqitafeiyong = Float.parseFloat(this.yundan.huicheqitafeiyong.getText().trim());
			}
			
//			jine = jine - huichezhesun + huicheqitafeiyong;
			jine = jine + huicheqitafeiyong;
//			this.yundan.huicheyingfuzonge += jine;
//			if(!(this.yundan.huicheshifujine.getText().trim().equals("") || this.yundan.huicheshifujine.getText().trim() == null)){
//				this.yundan.huicheshifuzonge += Float.parseFloat(this.yundan.huicheshifujine.getText().trim());
//			}
			
			float huicheshouxufei;
			if(this.yundan.huicheshouxufei.getText().trim().equals("") || this.yundan.huicheshouxufei.getText().trim() == null){
				huicheshouxufei = 0;
			} else {
				huicheshouxufei = Float.parseFloat(this.yundan.huicheshouxufei.getText().trim());
			}
			rec_vector.addElement(huicheshouxufei);
			jine = jine - huicheshouxufei;
			
			rec_vector.addElement(jine);
			rec_vector.addElement(this.yundan.huicheshifujine.getText().trim());
			rec_vector.addElement(this.yundan.huichejiezhangbeizhu.getText().trim());
			rec_vector.addElement(this.yundan.huicheshifouqingsuan.getSelectedItem().toString().trim());
			this.yundan.huichehuodanV.addElement(rec_vector);
			this.yundan.huichehuoming.setText("");
			this.yundan.huichezhongliang.setText("");
			this.yundan.huichezhongliang2.setText("");
			this.yundan.huichejiage.setText("");
			this.yundan.huichebaodijia.setText("");
//			this.yundan.huichezhesun.setText("");
			this.yundan.huichehuozhu.setText("");
			this.yundan.huicheqitafeiyong.setText("");
			this.yundan.huichebeizhu.setText("");
			this.yundan.huicheshifujine.setText("");
			this.yundan.huichejiezhangbeizhu.setText("");
			this.yundan.huicheshifouqingsuan.setSelectedItem("否");
			this.yundan.huichehuodanTM.fireTableStructureChanged();
		}
	}

}
