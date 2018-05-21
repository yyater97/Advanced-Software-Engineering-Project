<?php
	include('database.php');
	class m_demo extends database{
		
		//Lay gia tri cac hang muc chi tieu
		function getTypeExpense($type_expenseName){
			$sql = "SELECT * FROM type_expense WHERE type_expenseName = '$$type_expenseName'";
			$this->setQuery($sql);
			return $this->loadAllRows();
		}

		//Them comment vao bai viet
		function setTypeExpense($type_expenseID,$type_expenseName,$description){
			$sql="INSERT INTO type_expense($type_expenseID,$type_expenseName,$description) VALUES(?,?,?)";
			$this->setQuery($sql);
			return $this->execute(array($type_expenseID,$type_expenseName,$description));
		}


		//Them luot xem
		function upadteTypeExpense($_MaSK){
			$sql="UPDATE sukien SET SoLuotXem = SoLuotXem+1 WHERE MaSK=?";
			$this->setQuery($sql);
			return $this->execute(array($_MaSK));
		}

	}
?>