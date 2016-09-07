package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

public class ChuchetianjiaBtnActionListener implements ActionListener {

	YunDan yundan;
	
	ChuchetianjiaBtnActionListener(YunDan yd){
		this.yundan = yd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		int nullflag ,flag;
		nullflag = 0;
		flag = 0;
		if(this.yundan.chuchehuowubianhao.getText().trim().equals("") || this.yundan.chuchehuowubianhao.getText().trim() == null){
			nullflag = 1;
		} else {
			for(int i = 0; i < this.yundan.chuchehuodanTM.getRowCount(); i++){
//				System.out.println(this.yundan.chuchehuodanTM.getValueAt(i, 0));
				String str = "0" + this.yundan.chuchehuowubianhao.getText().trim();
				if(str.equals(this.yundan.chuchehuodanTM.getValueAt(i, 0))){
					flag = 1;
				}
			}
		}
		if(nullflag == 1){
			JOptionPane.showMessageDialog(null, "货物编号不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(flag == 1){
			JOptionPane.showMessageDialog(null, "货物编号不能重复！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchehuoming.getSelectedItem() == null || this.yundan.chuchehuoming.getSelectedItem().toString().trim().equals("")){
			JOptionPane.showMessageDialog(null, "货名不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchezhongliang.getText().trim().equals("") || this.yundan.chuchezhongliang.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "重量不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchehuozhu.getSelectedItem().toString().trim() == null || this.yundan.chuchehuozhu.getSelectedItem().toString().trim().equals("")){
			JOptionPane.showMessageDialog(null, "货主不能为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(((this.yundan.chuchejiage.getText().trim().equals("") || this.yundan.chuchejiage.getText().trim() == null))
				&& ((this.yundan.chuchebaodijia.getText().trim().equals("") || this.yundan.chuchebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "价格和保底价不能全为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!((this.yundan.chuchejiage.getText().trim().equals("") || this.yundan.chuchejiage.getText().trim() == null))
				&& !((this.yundan.chuchebaodijia.getText().trim().equals("") || this.yundan.chuchebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "价格和保底价只能有一个！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchezhongliang.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$") || Float.parseFloat(this.yundan.chuchezhongliang.getText().trim()) == 0){
			System.out.println(this.yundan.chuchezhongliang.getText().trim());
			JOptionPane.showMessageDialog(null, "重量必须为大于0的正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchezhongliang2.getText().trim().equals("") && this.yundan.chuchezhongliang2.getText().trim() != null && !this.yundan.chuchezhongliang2.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "重量2必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchejiage.getText().trim().equals("") && this.yundan.chuchejiage.getText().trim() != null && !this.yundan.chuchejiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "单价必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchebaodijia.getText().trim().equals("") && this.yundan.chuchebaodijia.getText().trim() != null && !this.yundan.chuchebaodijia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "保底价必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chucheqitafeiyong.getText().trim().equals("") && this.yundan.chucheqitafeiyong.getText().trim() != null && !this.yundan.chucheqitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "其他费用必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chucheshouxufei.getText().trim().equals("") && this.yundan.chucheshouxufei.getText().trim() != null && !this.yundan.chucheshouxufei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "出车手续费必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chucheshifujine.getText().trim().equals("") && this.yundan.chucheshifujine.getText().trim() != null && !this.yundan.chucheshifujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "实付金额必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if((this.yundan.chuchesijidanjia.getText().trim() == null || this.yundan.chuchesijidanjia.getText().trim().equals("")) && (this.yundan.chuchesijijine.getText().trim() == null || this.yundan.chuchesijijine.getText().trim().equals(""))){
			JOptionPane.showMessageDialog(null, "司机单价司机金额不能同时为空！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if((this.yundan.chuchesijidanjia.getText().trim() != null && !this.yundan.chuchesijidanjia.getText().trim().equals("")) && (this.yundan.chuchesijijine.getText().trim() != null && !this.yundan.chuchesijijine.getText().trim().equals(""))){
			JOptionPane.showMessageDialog(null, "司机单价司机金额只能填一个！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchesijidanjia.getText().trim().equals("") && this.yundan.chuchesijidanjia.getText().trim() != null && !this.yundan.chuchesijidanjia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "司机单价必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchesijijine.getText().trim().equals("") && this.yundan.chuchesijijine.getText().trim() != null && !this.yundan.chuchesijijine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "司机金额必须为正数！", "错误", JOptionPane.PLAIN_MESSAGE);
		} else {
			Vector rec_vector = new Vector();
			rec_vector.addElement("0"+this.yundan.chuchehuowubianhao.getText().trim());
			rec_vector.addElement(this.yundan.chuchehuoming.getSelectedItem().toString().trim());
			rec_vector.addElement(this.yundan.chuchezhongliang.getText().trim());
			rec_vector.addElement(this.yundan.chuchezhongliang2.getText().trim());
			rec_vector.addElement(this.yundan.chuchejiage.getText().trim());
			rec_vector.addElement(this.yundan.chuchebaodijia.getText().trim());
//			rec_vector.addElement(this.yundan.chuchezhesun.getText().trim());
			rec_vector.addElement(this.yundan.chuchehuozhu.getSelectedItem().toString().trim());
			rec_vector.addElement(this.yundan.chucheqitafeiyong.getText().trim());
			rec_vector.addElement(this.yundan.chuchebeizhu.getText().trim());
			float jine;
			if(this.yundan.chuchebaodijia.getText().trim().equals("") || this.yundan.chuchebaodijia.getText().trim() == null){
				jine = Float.parseFloat(this.yundan.chuchezhongliang.getText().trim())*Float.parseFloat(this.yundan.chuchejiage.getText().trim());
			} else {
				jine = Float.parseFloat(this.yundan.chuchebaodijia.getText().trim());
			}
			
//			float chuchezhesun, chucheqitafeiyong;
//			if(this.yundan.chuchezhesun.getText().trim().equals("") || this.yundan.chuchezhesun.getText().trim() == null){
//				chuchezhesun = 0;
//			} else {
//				chuchezhesun = Float.parseFloat(this.yundan.chuchezhesun.getText().trim());
//			}
			float chucheqitafeiyong;
			if(this.yundan.chucheqitafeiyong.getText().trim().equals("") || this.yundan.chucheqitafeiyong.getText().trim() == null){
				chucheqitafeiyong = 0;
			} else {
				chucheqitafeiyong = Float.parseFloat(this.yundan.chucheqitafeiyong.getText().trim());
			}
			
//			jine = jine - chuchezhesun + chucheqitafeiyong;
			jine = jine + chucheqitafeiyong;
			
//			this.yundan.chucheyingfuzonge += jine;
//			if(!(this.yundan.chucheshifujine.getText().trim().equals("") || this.yundan.chucheshifujine.getText().trim() == null)){
//				this.yundan.chucheshifuzonge += Float.parseFloat(this.yundan.chucheshifujine.getText().trim());
//			}
			
			float chucheshouxufei;
			if(this.yundan.chucheshouxufei.getText().trim().equals("") || this.yundan.chucheshouxufei.getText().trim() == null){
				chucheshouxufei = 0;
			} else {
				chucheshouxufei = Float.parseFloat(this.yundan.chucheshouxufei.getText().trim());
			}
			rec_vector.addElement(chucheshouxufei);
			jine = jine - chucheshouxufei;
			
			
			rec_vector.addElement(jine);
			rec_vector.addElement(this.yundan.chucheshifujine.getText().trim());
			rec_vector.addElement(this.yundan.chuchejiezhangbeizhu.getText().trim());
			if(this.yundan.chuchesijidanjia.getText().trim() != null && !this.yundan.chuchesijidanjia.getText().trim().equals("")){
				rec_vector.addElement(this.yundan.chuchesijidanjia.getText().trim());
				float chucheyingfusijijine;
				chucheyingfusijijine = Float.parseFloat(this.yundan.chuchezhongliang.getText().trim())*Float.parseFloat(this.yundan.chuchesijidanjia.getText().trim()) + chucheqitafeiyong - chucheshouxufei;
				rec_vector.addElement(chucheyingfusijijine);
			} else {
				float chuchesijijiage;
				chuchesijijiage = (Float.parseFloat(this.yundan.chuchesijijine.getText().trim()) - chucheqitafeiyong + chucheshouxufei)/Float.parseFloat(this.yundan.chuchezhongliang.getText().trim());
				rec_vector.addElement(chuchesijijiage);
				rec_vector.addElement(this.yundan.chuchesijijine.getText().trim());
			}
			rec_vector.addElement(this.yundan.chucheshifouqingsuan.getSelectedItem().toString().trim());
			
			this.yundan.chuchehuodanV.addElement(rec_vector);
			String str = this.yundan.chuchehuowubianhao.getText();
			str = String.valueOf((Integer.parseInt(str)+1));
			this.yundan.chuchehuowubianhao.setText(str);
			this.yundan.chuchehuoming.removeAllItems();
			this.yundan.chuchezhongliang.setText("");
			this.yundan.chuchezhongliang2.setText("");
			this.yundan.chuchejiage.setText("");
			this.yundan.chuchebaodijia.setText("");
//			this.yundan.chuchezhesun.setText("");
			this.yundan.chuchehuozhu.setSelectedIndex(0);
			this.yundan.chucheqitafeiyong.setText("");
			this.yundan.chuchebeizhu.setText("");
			this.yundan.chucheshifujine.setText("");
			this.yundan.chuchejiezhangbeizhu.setText("");
			this.yundan.chuchesijidanjia.setText("");
			this.yundan.chuchesijijine.setText("");
			this.yundan.chucheshifouqingsuan.setSelectedItem("否");
			this.yundan.chuchehuodanTM.fireTableStructureChanged();
		}
	}

}
