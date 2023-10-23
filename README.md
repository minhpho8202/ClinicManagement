# ClinicManagement

Các phần mềm cần có:
- JDK 20 (https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)
- Apache Tomcat 9 (https://tomcat.apache.org/download-90.cgi)
- Apache NetBeans IDE 18 (https://netbeans.apache.org/download/nb18/)
- Visual Studio Code (https://code.visualstudio.com/download)
- MySQL Workbench 8.0 CE (https://dev.mysql.com/downloads/workbench/)

Khởi động phần mềm:

Phía admin:

Mở MySQL Workbench 8.0 CE:
- Tạo mới một database với tên "clinicmanagement"
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/59c09db2-27bd-49c9-a657-55e2763cfadd)
- Import file database:
  + Server -> Data import -> Chọn Import from Self-Contained File -> Chọn đường dẫn đến file clinicmanagementdb.sql
  + Default Target Schema -> Chọn "clinicmanagement"
  + Start Import
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/809a5f8f-2da4-4a12-a00b-e279e8c31374)
  + Refresh lại database và kiểm tra. Nếu xuất hiện các bảng như hình là đúng.
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/5b83e71d-139b-4a85-9f99-09a531fa6ef2)

Mở Apache NetBeans IDE 18:
- Mở project SpringMVCClinicManagement.
- Mở file databases.properties và sửa username và password cho phù hợp.
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/7217d497-59f9-48c9-87e6-6d47a13cfc14)
- Bấm chuột phải vào project và bấm "Build".
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/db6de9f0-ed07-4e64-b0ef-2db176093b51)
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/9cd401b2-5899-49af-a4d6-512d068bffe9)
- Sau khi build xong thì bấm "Run".
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/272f381d-c036-4642-941b-279de6233fab)
-Khi thấy trang web này hiện lên thì quá trình khởi động phía admin thành công.
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/21621419-ece9-41cb-b992-8d145b4491ef)

Phía người dùng:

Mở Visual Studio Code:

- Mở project reactjsclinicmanagement.
- Mở Command Prompt:
  + Terminal -> New Terminal
  + Chọn command prompt
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/d2a1880f-efa6-4ce5-a530-273b97a1ba13)
- Gõ lệnh "npm install" để cài đặt các thư viện.
- Sau khi cài đặt xong các thư viện thì gõ lệnh "yarn start" để khởi động trang web. Khi thấy trang web này hiện lên thì khởi động thành công.
![image](https://github.com/minhpho8202/ClinicManagement/assets/92845747/6a14bc26-af53-4153-a12f-685d95ce18d7)

