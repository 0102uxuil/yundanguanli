package wuliuxitong;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;

public class HuoWuquedingBtnActionListener implements ActionListener {

	HuoWu huowu;
	
	HuoWuquedingBtnActionListener(HuoWu hw){
		this.huowu = hw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.huowu.huoming.getText().trim().equals("") || this.huowu.huoming.getText().trim() == null){
			JOptionPane.showMessageDialog(this.huowu, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(this.huowu.zhongliang.getText().trim().equals("") || this.huowu.zhongliang.getText().trim() == null){
			JOptionPane.showMessageDialog(this.huowu, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(this.huowu.huozhu.getText().trim().equals("") || this.huowu.huozhu.getText().trim() == null){
			JOptionPane.showMessageDialog(this.huowu, "��������Ϊ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(((this.huowu.jiage.getText().trim().equals("") || this.huowu.jiage.getText().trim() == null))
				&& ((this.huowu.baodijia.getText().trim().equals("") || this.huowu.baodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(this.huowu, "�۸�ͱ��׼۲���ȫΪ�գ�", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!((this.huowu.jiage.getText().trim().equals("") || this.huowu.jiage.getText().trim() == null))
				&& !((this.huowu.baodijia.getText().trim().equals("") || this.huowu.baodijia.getText().trim() == null))){
			JOptionPane.showMessageDialog(this.huowu, "�۸�ͱ��׼�ֻ����һ����", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.zhongliang.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "��������Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.zhongliang2.getText().trim().equals("") && this.huowu.zhongliang2.getText().trim() != null && !this.huowu.zhongliang2.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "����2����Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.jiage.getText().trim().equals("") && this.huowu.jiage.getText().trim() != null && !this.huowu.jiage.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "���۱���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.baodijia.getText().trim().equals("") && this.huowu.baodijia.getText().trim() != null && !this.huowu.baodijia.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "���׼۱���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.qitafeiyong.getText().trim().equals("") && this.huowu.qitafeiyong.getText().trim() != null && !this.huowu.qitafeiyong.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "�������ñ���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.shouxufei.getText().trim().equals("") && this.huowu.shouxufei.getText().trim() != null && !this.huowu.shouxufei.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "�����ѱ���Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else if(!this.huowu.shifujine.getText().trim().equals("") && this.huowu.shifujine.getText().trim() != null && !this.huowu.shifujine.getText().trim().matches("^([1-9][0-9]*[.][0-9]*|0[.][0-9]+|[1-9][0-9]*|0)$")){
			JOptionPane.showMessageDialog(this.huowu, "ʵ��������Ϊ������", "����", JOptionPane.PLAIN_MESSAGE);
		} else {
			this.huowu.tm.setValueAt(this.huowu.huoming.getText().trim(), this.huowu.selectedRow, 1);
			this.huowu.tm.setValueAt(this.huowu.zhongliang.getText().trim(), this.huowu.selectedRow, 2);
			this.huowu.tm.setValueAt(this.huowu.zhongliang2.getText().trim(), this.huowu.selectedRow, 3);
			this.huowu.tm.setValueAt(this.huowu.jiage.getText().trim(), this.huowu.selectedRow, 4);
			this.huowu.tm.setValueAt(this.huowu.baodijia.getText().trim(), this.huowu.selectedRow, 5);
			this.huowu.tm.setValueAt(this.huowu.huozhu.getText().trim(), this.huowu.selectedRow, 6);
			this.huowu.tm.setValueAt(this.huowu.qitafeiyong.getText().trim(), this.huowu.selectedRow, 7);
			this.huowu.tm.setValueAt(this.huowu.beizhu.getText().trim(), this.huowu.selectedRow, 8);
			this.huowu.tm.setValueAt(this.huowu.shouxufei.getText().trim(), this.huowu.selectedRow, 9);
			float yingfujine;
			if(this.huowu.baodijia.getText().trim().equals("") || this.huowu.baodijia.getText().trim() == null){
				yingfujine = Float.parseFloat(this.huowu.zhongliang.getText().trim())*Float.parseFloat(this.huowu.jiage.getText().trim());
			} else {
				yingfujine = Float.parseFloat(this.huowu.baodijia.getText().trim());
			}
			
			float qitafeiyong;
			if(this.huowu.qitafeiyong.getText().trim().equals("") || this.huowu.qitafeiyong.getText().trim() == null){
				qitafeiyong = 0;
			} else {
				qitafeiyong = Float.parseFloat(this.huowu.qitafeiyong.getText().trim());
			}
			yingfujine = yingfujine + qitafeiyong;
			
			this.huowu.tm.setValueAt(yingfujine, this.huowu.selectedRow, 10);
			this.huowu.tm.setValueAt(this.huowu.shifujine.getText().trim(), this.huowu.selectedRow, 11);
			this.huowu.tm.setValueAt(this.huowu.jiezhangbeizhu.getText().trim(), this.huowu.selectedRow, 12);
			this.huowu.tm.setValueAt(this.huowu.shifouqingsuan.getSelectedItem().toString(), this.huowu.selectedRow, 13);
			
			this.huowu.dispose();
			this.huowu.tm.fireTableDataChanged();
			JOptionPane.showMessageDialog(this.huowu, "������Ϣ���޸ģ�", "�޸Ļ���", JOptionPane.PLAIN_MESSAGE);
		}
//		this.huowu.dispose();
//		this.huowu.tm.fireTableDataChanged();
//		JOptionPane.showMessageDialog(this.huowu, "������Ϣ���޸ģ�", "�޸Ļ���", JOptionPane.PLAIN_MESSAGE);
	}

}
