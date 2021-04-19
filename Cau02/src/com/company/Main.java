package com.company;

import java.util.*;

public class Main {
    static List<Device> deviceList = new ArrayList<>();
    static List<Device> deviceListSort = new ArrayList<>();
    static Scanner scanner;
    static int device_balance, device_count;
    static int slDaMua = 0;
    static int soTienDaTra = 0;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        System.out.print("Nhập số tiền được cấp để mua thiết bị: ");
        device_balance = scanner.nextInt();

        System.out.print("Nhập số thiết bị cần mua: ");
        device_count = scanner.nextInt();

        menuChinh();

    }

    private static void menuChinh() {
        System.out.println("Menu: ");
        System.out.println("1. Thêm thiết bị.");
        System.out.println("2. Edit thiết bị by id (name, price).");
        System.out.println("3. Xóa thiết bị by id.");
        System.out.println("4. Sort thiêt bị theo giá tiền.");
        System.out.println("5. Sort thiết bị theo tên.");
        System.out.println("6. Hiển thị thông tin thiết bị.");
        System.out.println("0. Exit .");

        System.out.print("Chọn chức năng: ");
        int chon = scanner.nextInt();

        switch (chon) {
            case 0 -> System.out.print("Kết thúc chương trình.");
            case 1 -> themThietBi();
            case 2 -> editThietBi();
            case 3 -> xoaThietBi();
            case 4 -> sortTheoGiaTien();
            case 5 -> sortTheoTen();
            case 6 -> hienThiThongTinThietBi();
        }
    }

    private static void themThietBi() {
        System.out.println("Số tiền còn lại để mua thiết bị: " + (device_balance - soTienDaTra));
        System.out.println("Số thiết bị còn lại có thể mua: " + (device_count - slDaMua));
        System.out.print("Nhập ID thiết bị: ");
        int ID = scanner.nextInt();

        if (deviceList.size() > 0) {
            //Kiếm tra ID có bị trùng hay không?
            boolean trungID = false;
            for (Device device : deviceList) {
                if (device.getID() == ID) {
                    trungID = true;
                    break;
                }
            }

            if (trungID) {
                System.out.println("Lỗi trùng ID -> Dừng chương trình.");
            } else {
                checkBalanceKhiThemThietBi(ID);
            }
        } else {
            checkBalanceKhiThemThietBi(ID);
        }
    }

    private static void checkBalanceKhiThemThietBi(int ID) {
        System.out.print("Nhập tên thiết bị: ");
        String name = scanner.next();
        System.out.print("Nhập đơn giá thiết bị: ");
        int price = scanner.nextInt();
        System.out.print("Nhập số lượng thiết bị cần mua: ");
        int slMua = scanner.nextInt();

        int totalPrice = device_count * price;

        //Check balance
        int daMua = 0;
        for (Device value : deviceList) {
            daMua += (value.getPrice() * value.getDevice_count());
        }

        if ((slMua + slDaMua) > device_count) {
            System.out.println("Lỗi vượt quá số lượng cần mua -> dừng chương trình");
        } else {
            slDaMua += slMua;
        }

        if ((daMua + totalPrice) > device_balance) {
            System.out.println("Lỗi không đủ tiền -> dừng chương trình");
        } else {
            soTienDaTra += totalPrice;
            //Thêm thiết bị vào list
            Device device = new Device(ID, price, device_count, name);
            deviceList.add(device);
            System.out.println("Thêm thiết bị vào giỏ hàng thành công!");
            System.out.println("Số tiền còn lại: " + (device_balance - (daMua + totalPrice)));
            System.out.println("Số thiết bị còn lại: " + (device_count - slDaMua));
            menuChinh();
        }
    }

    private static void editThietBi() {
        System.out.print("Nhập ID thiết bị cần sửa: ");
        int ID = scanner.nextInt();
        boolean dungID = false;
        boolean suaThanhCong = false;

        for (int i = 0; i < deviceList.size(); i++) {
            if (deviceList.get(i).getID() == ID) {
                dungID = true;
                // Đọc tên và sửa thiết bị
                Device tb = deviceList.get(i);
                System.out.print("Sửa thiết bị tên " + tb.getName() + " thành: ");
                String name = scanner.next();
                System.out.print("Thiết bị " + name + " có giá cũ là " + tb.getPrice() + " giờ sửa giá mới là: ");
                int price = scanner.nextInt();

                //Ghi tạm vào list
                deviceList.get(i).setName(name);
                deviceList.get(i).setPrice(price);

                //Kiểm tra có vượt quá số tiền được cấp không
                int sum = 0;
                for (Device device : deviceList) {
                    sum += (device.getPrice() * device.getDevice_count());
                }

                if (sum > device_count) {
                    System.out.println("Lỗi vượt quá số tiền được cấp -> kết thúc chương trình");
                    break;
                } else {
                    suaThanhCong = true;
                }
            }
        }

        if (!dungID) {
            System.out.println("Không đúng ID -> Quay về trang chủ");
        }

        if (suaThanhCong) {
            System.out.println("Sửa thiết bị có ID: " + ID + " thành công");
        } else {
            System.out.println("Sửa thiết bị thất bại");
        }

        menuChinh();
    }

    private static void xoaThietBi() {
        System.out.print("Nhập ID thiết bị cần xóa: ");
        int ID = scanner.nextInt();
        boolean xoaThanhCong = false;
        //Xóa ID
        for (int i = 0; i < deviceList.size(); i++) {
            if (deviceList.get(i).getID() == ID) {
                deviceList.remove(i);
                xoaThanhCong = true;
                break;
            }
        }

        if (xoaThanhCong) {
            System.out.println("Xóa thiết bị có ID: " + ID + " thành công");
        } else {
            System.out.println("Không tìm thấy ID trong danh sách thiết bị");
        }

        menuChinh();
    }

    private static void sortTheoGiaTien() {
        deviceListSort = deviceList;
        deviceListSort.sort(Comparator.comparing(Device::getPrice));
        System.out.println("ID\tName\tPrice\tDevice_Count");

        for (Device device : deviceListSort) {
            System.out.println(device.getID() + "\t" + device.getName() + "\t" + device.getDevice_count() + "\n");
        }
    }

    private static void sortTheoTen(){
        deviceListSort = deviceList;
        deviceListSort.sort(Comparator.comparing(Device::getName));
        System.out.println("ID\tName\tPrice\tDevice_Count");

        for (Device device : deviceListSort) {
            System.out.println(device.getID() + "\t" + device.getName() + "\t" + device.getDevice_count() + "\n");
        }
    }
    
    private static void hienThiThongTinThietBi(){
        System.out.print("Nhập ID thiết bị cần hiển thị: ");
        int ID = scanner.nextInt();
        boolean coThietBi = false;
        
        Device deviceHienThi = null;
        
        for (Device device : deviceList) {
            if(device.getID() == ID){
                coThietBi = true;
                deviceHienThi = device;
            }
        }
        
        if(coThietBi){
            System.out.println("Thông tin chi tiết thiết bị là: ");
            System.out.println("ID\tName\tPrice\tDevice_Count");
            System.out.println(deviceHienThi.getID() + "\t" + deviceHienThi.getName() + "\t" + deviceHienThi.getDevice_count() + "\n");
        } else {
            System.out.println("Không tìm thấy thông tin chi tiết thiết bị");
        }

        menuChinh();
    }

}
