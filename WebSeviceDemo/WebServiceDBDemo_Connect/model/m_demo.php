<?php
	include('database.php');
	class m_demo extends database{
		
		//Lay gia tri cac hang muc chi tieu
		function getTypeExpense(){
			$sql = "SELECT type_expenseName, description FROM type_expense";
			$this->setQuery($sql);
			return $this->loadAllRows();
		}
	}
?>