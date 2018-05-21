<?php
	include('model/m_demo.php');

	class c_demo{
		public function selectTypeExpense($type_expenseName){
			$m_Demo = new m_demo();
			$typeExpense = $m_Demo->getTypeExpense($type_expenseName);
			return array('typeExpense'=>$typeExpense);
		}

		public function insertTypeExpense($type_expenseID,$type_expenseName,$description){
			$m_Demo = new m_demo();
			$typeExpense = $m_Demo->setTypeExpense($type_expenseID,$type_expenseName,$description);
			if($typeExpense)
				return true;
			else
				return false;
		}
	}

?>