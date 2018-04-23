# Advanced-Software-Engineering-Project
Đồ án công nghệ phần mềm chuyên sâu

##Phần mềm quản lý tài chính cá nhân  
GVHD: Ths.Nguyễn Công Hoan

1. Các thành viên trong nhóm thực hiện đồ án
- Phạm Ngọc Quân – MSSV: 15520680
- Dương Văn Thanh – MSSV: 15520801
- Nguyễn Trung Quân – MSSV: 15520679
- Trần Hưng Quang – MSSV: 15520693

2. Ngôn ngữ lập trình & cơ sở dữ liệu  
Phần mềm được viết bằng ngôn ngữ Java với nền tảng Android, sử dụng cơ sở dữ liệu MySQL  
Sử dụng công cụ Andorid studio IDE và MySQL (Xampp, máy chủ Apache)  
(Trước khi sử dụng phần mềm yêu cầu quý khách hàng cài đặt SQL Sever và thêm file cơ sở dữ liệu vào hệ thống)

3. Các chức năng của phần mềm  
Phần mềm quản lý tài chính cá nhân gồm có các chức năng như sau:  
- Quản lý tài khoản của khách hàng  
	Phần mềm sẽ quản lý một trong các tài khoản sau Ví tiền, tài khoản ATM, sổ tiết kiệm hoặc tài khoản ngân hàng, số tiền trong các tài khoản sẽ được tự động cập nhật thông qua các hoạt động thu chi của khách hàng  
- Quản lý tình trạng thu chi  
	Phần mềm sẽ kiểm soát tình hình thu nhập và chi tiêu của khách hàng thông qua các bản ghi chú. 
	Đối với thu nhập sẽ bao gồm các nguồn thu như: tiền lương, tiền làm thêm, tiền vay hoặc được cấp từ nguồn khác (sử đối với các đối tượng chưa có khả năng lao động kiếm tiền)
	Đối với chi tiết sẽ bao gồm các mục như: ăn uống, sinh hoạt (điện, nước,…), đi lại (xăng, tiền sửa xe,…), mua sắm, con cái (nếu có gia đình), một số mục khác.
- Tra cứu tỷ giá  
	Phần mềm cung cấp cho khách hàng bảng tỷ giá giữa các loại tiền đang lưu hành của các nước mới nhất thông qua mạng internet, đồng thời cho khách hàng đổi một số tiền tương ứng giữa hai loại tiền bất kỳ.  
- Báo cáo tình hình thu chi  
	Phần mềm sẽ thống kê tình hình thu chi của khách hàng theo từng ngày, tuần, tháng, năm.  
	Báo cáo thông qua biểu đồ trực quan giúp khách hàng biết rõ mình đã chi tiêu như thế nào, từ đó có sự điều chỉnh cho phù hợp và hiệu quả hơn.  

4. Lợi ích khi sử dụng phần mềm quản lý tài chính cá nhân  
Phần mềm quản lý tài chính cá nhân sẽ giúp khách hàng biết được tình trạng thu nhập và chi tiêu của mình, các hoạt động tiêu dùng đã hợp lý hay chưa (số tiền bỏ ra có vượt quá hay còn dư so với thu nhập kiếm được), từ đó nhắc nhở khách hàng phải chi tiêu hợp lý hơn tránh trường hợp đầu và giữa tháng chi quá mạnh tay đến cuối tháng hết tiền.  
Đồng thời phần mềm còn cung cấp tỷ giá giữ các loại tiền đang lưu hành trên thị trường, hỗn trợ khách hàng chọn thời điểm khi muốn đổi tiền một cách tốt nhất.  

5. Ưu nhược điểm của phần mềm  
- Ưu điểm:  
	Phần mềm đáp ứng nhu cầu quản lý tài chính cá nhân cơ bản của khách hàng.  
	Phần mềm có các tính năng hữu ích, trực quan, dễ sử dụng.  
- Nhược điểm:  
	Phần mềm chưa có server để đồng bộ dữ liệu của khách hàng để trong trường hợp dữ liệu trên máy tính bị hư hại hoặc bị mất có thể cập nhật lại được  
	Phần mềm chỉ có một phiên bản trên thiết bị di động (Android), chưa hỗ trợ trên một số nền tảng phổ biến khác (IOS, Linux, Windows,...)  