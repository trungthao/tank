#Tank

## Đã làm
###Các object chính
####Tank
Có các phương thức
1. Vẽ tank
2. di chuyển 1 pixel theo hướng định sẵn
####TankPlayer (extends Tank)
di chuyển bằng các phím mũi tên
bắn đạn bằng phím space
####TankOther )extend Tank)
tự động di chuyển
tự động bắn
####TankOtherManager
vẽ toàn bộ tankOther
cho toàn bộ tankOther di chuyển
####Bullet
Vẽ đạn
Di chuyển
kiểm tra khi đạn bắn trúng tank
####BulletManager
Vẽ toàn bộ đạn
Xử lý khi đạn va chạm với tank

###Chức năng hiện tại
1. Tank màu vàng di chuyển được, bắn được
2. Tank màu xanh tự động di chuyển
3. Tank vàng bắn đạn vào tank xanh thì tank xanh biến mất, đạn biến mất
4. Các tank xanh tự động đổi hướng nếu va chạm vào nhau

##Sắp làm
1. xử lý sự kiện Tank vàng va chạm với Tank xanh
2. Thiết lập map
3. Kiểm tra khi tank va chạm với map
4. Thiết lập các button play, reset, pause