package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tổng các phần tử (n): ");
        int n = scanner.nextInt();

        if (n > 0) {
            int[] mang = new int[n];
            int sum = 0;

            //Tính tổng
            for (int i = 0; i < n; i++) {
                System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
                mang[i] = scanner.nextInt();
                sum += mang[i];
            }

            System.out.println("Tổng của các phần tử trong mảng là: " + sum);

            System.out.print("Nhập 1 số bất kỳ để tìm vị trí: ");

            int soBatKy = scanner.nextInt();

            //Tìm vị trí
            System.out.print("Index của số " + soBatKy + " trong mảng là: ");
            boolean tonTaiViTri = false;

            for (int i = 0; i < n; i++) {
                if(soBatKy == mang[i]){
                    tonTaiViTri = true;
                    System.out.print(i + " ");
                }
            }

            if(!tonTaiViTri) System.out.println("Không tồn tại vị trí");
        }

    }
}
