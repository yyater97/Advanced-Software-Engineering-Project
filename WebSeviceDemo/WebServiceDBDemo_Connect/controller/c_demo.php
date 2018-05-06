<?php
	include('model/m_demo.php');

	class c_demo{
		public function TypeExpense(){
			$m_Demo = new m_demo();
			$typeExpense = $m_Demo->getTypeExpense();
			return array('typeExpense'=>$typeExpense);
		}
	}

?>