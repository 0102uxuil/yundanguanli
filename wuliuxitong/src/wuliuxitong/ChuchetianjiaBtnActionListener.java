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
			JOptionPane.showMessageDialog(null, "�����Ų���Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(flag == 1){
			JOptionPane.showMessageDialog(null, "�����Ų����ظ���", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchehuoming.getText().trim().equals("") || this.yundan.chuchehuoming.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchezhongliang.getText().trim().equals("") || this.yundan.chuchezhongliang.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(this.yundan.chuchehuozhu.getText().trim().equals("") || this.yundan.chuchehuozhu.getText().trim() == null){
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(((this.yundan.chuchejiage.getText().trim().equals("") || this.yundan.chuchejiage.getText().trim() == null))
				&& ((this.yundan.chuchebaodijia.getText().trim().equals("") || this.yundan.chuchebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "�۸�ͱ��׼۲���ȫΪ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!((this.yundan.chuchejiage.getText().trim().equals("") || this.yundan.chuchejiage.getText().trim() == null))
				&& !((this.yundan.chuchebaodijia.getText().trim().equals("") || this.yundan.chuchebaodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(null, "�۸�ͱ��׼�ֻ����һ����", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchezhongliang.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			System.out.println(this.yundan.chuchezhongliang.getText().trim());
			JOptionPane.showMessageDialog(null, "��������Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchezhongliang2.getText().trim().equals("") && this.yundan.chuchezhongliang2.getText().trim() != null && !this.yundan.chuchezhongliang2.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "����2����Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchejiage.getText().trim().equals("") && this.yundan.chuchejiage.getText().trim() != null && !this.yundan.chuchejiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "���۱���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chuchebaodijia.getText().trim().equals("") && this.yundan.chuchebaodijia.getText().trim() != null && !this.yundan.chuchebaodijia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "���׼۱���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chucheqitafeiyong.getText().trim().equals("") && this.yundan.chucheqitafeiyong.getText().trim() != null && !this.yundan.chucheqitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "�������ñ���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.yundan.chucheshifujine.getText().trim().equals("") && this.yundan.chucheshifujine.getText().trim() != null && !this.yundan.chucheshifujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(null, "ʵ��������Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else {
			Vector rec_vector = new Vector();
			rec_vector.addElement("0"+this.yundan.chuchehuowubianhao.getText().trim());
			rec_vector.addElement(this.yundan.chuchehuoming.getText().trim());
			rec_vector.addElement(this.yundan.chuchezhongliang.getText().trim());
			rec_vector.addElement(this.yundan.chuchezhongliang2.getText().trim());
			rec_vector.addElement(this.yundan.chuchejiage.getText().trim());
			rec_vector.addElement(this.yundan.chuchebaodijia.getText().trim());
//			rec_vector.addElement(this.yundan.chuchezhesun.getText().trim());
			rec_vector.addElement(this.yundan.chuchehuozhu.getText().trim());
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
			rec_vector.addElement(jine);
			rec_vector.addElement(this.yundan.chucheshifujine.getText().trim());
			rec_vector.addElement(this.yundan.chuchejiezhangbeizhu.getText().trim());
			rec_vector.addElement(this.yundan.chucheshifouqingsuan.getSelectedItem().toString().trim());
			
			this.yundan.chuchehuodanV.addElement(rec_vector);
			this.yundan.chuchehuoming.setText("");
			this.yundan.chuchezhongliang.setText("");
			this.yundan.chuchezhongliang2.setText("");
			this.yundan.chuchejiage.setText("");
			this.yundan.chuchebaodijia.setText("");
//			this.yundan.chuchezhesun.setText("");
			this.yundan.chuchehuozhu.setText("");
			this.yundan.chucheqitafeiyong.setText("");
			this.yundan.chuchebeizhu.setText("");
			this.yundan.chucheshifujine.setText("");
			this.yundan.chuchejiezhangbeizhu.setText("");
			this.yundan.chucheshifouqingsuan.setSelectedItem("��");
			this.yundan.chuchehuodanTM.fireTableStructureChanged();
		}
	}

}