import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShyourBox {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Cart> carts = new ArrayList<Cart>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) {
        ShyourBox shyourboxApp = new ShyourBox();
        System.out.println("Welcome to ShyourBox! Yuk beli jangan shy shy!");

        String productAddress = "input/daftarProduk.txt";
        String customerAddress = "input/daftarCustomer.txt";

        shyourboxApp.addCustomer(customerAddress);
        shyourboxApp.addProduct(productAddress);

        Scanner masukan = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        int choice;
        String name = "";
        do {
            System.out.println("Menu" +
                    "\n1. Beli Produk" +
                    "\n2. Cari Produk" +
                    "\n3. Print Struk" +
                    "\n0. Keluar");
            System.out.print("Pilih menu: ");
            choice = masukan.nextInt();

            switch (choice) {
                case 1:
                    shyourboxApp.buyProduct();
                    break;
                case 2:
                    System.out.print("Cari produk dengan nama: ");
                    name = input2.nextLine();
                    shyourboxApp.searchProduct(name);
                    break;
                case 3:
                    shyourboxApp.printReceipt();
                    break;
                case 0:
                    System.out.println("Yay!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice != 0);
        masukan.close();
    }

    /**
     * Method untuk membeli produk.
     */
    public void buyProduct() {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama customer: ");
        String customerName = input.nextLine();
        Customer customer = findCustomer(customerName);
        if (customer == null) {
            System.out.println("Mohon maaf, customer atas nama " + customerName + " tidak terdaftar!");
            return;            
        }
        System.out.println("====MASUKKAN ITEM KE KERANJANG====");
        System.out.println();
        Cart cart = new Cart(customer);
        String productName;
        int quantity;
        do {
            System.out.print("Masukkan nama produk: ");
            productName = input.nextLine();
            if (productName == "") {
                productName = input.nextLine();
            }
            Product product = findProduct(productName);
            if (productName.equalsIgnoreCase("X")) {
                break;
            }
    
            if (product == null) {
                System.out.println("Mohon maaf, produk tidak tersedia!");
            } else {
                System.out.print("Masukkan Jumlah (kg): ");
                while (!input.hasNextInt()) {
                    System.out.println("Mohon masukkan angka yang valid!");
                    input.next(); 
                }
                quantity = input.nextInt();
                if (quantity <= product.getStock()) {
                    OrderItem orderItem = new OrderItem(product, quantity);
                    cart.addOrderItem(orderItem);
                    product.setStock(product.getStock()-quantity);
                    System.out.println("Produk Berhasil ditambahkan!");
                    System.out.println();
                } else {
                    System.out.println("Mohon maaf, stok tidak mencukupi!");
                }
            }
        } while (!productName.equalsIgnoreCase("X"));
        carts.add(cart);
        System.out.println("Terima kasih sudah berbelanja, " + customerName + "!");
    }
    

    /**
     * Method untuk mencari produk berdasarkan nama.
     * 
     * @param name
     * @return
     */
    public Product searchProduct(String name) {
        for (Product product : products) {
            if (product.getNama().equalsIgnoreCase(name)) {
                System.out.println("Produk Ditemukan!");
                System.out.println("[" + (product instanceof Fruit ? "Buah" : "Sayuran") + " " + (product.isLocal() ? "Lokal" : "Impor") + "]");
                System.out.println("Nama Produk: " + product.getNama());
                System.out.println("Harga: " + product.getPrice());
                System.out.println("Stok: " + product.getStock());
                return product;
            }
        }
        System.out.println("Produk dengan nama '" + name + "' tidak ditemukan.");
        return null;
    }

    /**
     * Method untuk menambahkan produk pada file txt ke dalam list produk.
     * 
     * @param fileAddress
     */
    public void addProduct(String fileAddress) {
        try {
            File file = new File(fileAddress);
            Scanner scanner = new Scanner(file);
            int successCount = 0;
            int errorCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(", ");
                if (data.length == 5) {
                    String type = data[0];
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);
                    int stock = Integer.parseInt(data[3]);
                    boolean isLocal = data[4].equalsIgnoreCase("Lokal");
                    if (type.equalsIgnoreCase("Fruit")) {
                        Fruit fruit = new Fruit(name, price, stock, isLocal);
                        products.add(fruit);
                        successCount++;
                    } else if (type.equalsIgnoreCase("Veggie")) {
                        Veggie veggie = new Veggie(name, price, stock, !isLocal);
                        products.add(veggie);
                        successCount++;
                    } else {
                        errorCount++;
                    }
                } else {
                    errorCount++;
                }
            }
            System.out.println("Berhasil menambahkan " + successCount + " Produk");
            System.out.println("Gagal menambahkan " + errorCount + " Produk");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method untuk menambahkan customer pada file txt ke dalam list customer.
     * 
     * @param fileAddress
     */
    public void addCustomer(String fileAddress) {
        try {
            File file = new File(fileAddress);
            Scanner scanner = new Scanner(file);
            int successCount = 0;
            int errorCount = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(", ");
                if (data.length == 2) {
                    String name = data[0];
                    boolean isPremium = data[1].equalsIgnoreCase("premium");
                    Customer customer = new Customer(name, isPremium);
                    customers.add(customer);
                    if (data[1].equals("premium") || data[1].equals("reguler")) {
                    successCount++;
                }
                else {
                    errorCount++;
                }
                    
                } 
                else {
                    errorCount++;
                }
            }
            System.out.println("Berhasil menambahkan " + successCount + " Customer");
            System.out.println("Gagal menambahkan " + errorCount + " Customer");
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method untuk mencetak struk belanja pada file txt.
     */
    public void printReceipt() {
        try {
            FileWriter writer = new FileWriter("Struk.txt");
            writer.write("Berikut adalah rekap perbelanjaan hari ini:\n");
            writer.write("=============================\n");

            for (Cart cart : carts) {
                writer.write("Nama Customer: " + cart.getCustomerName().getName() + "\n");
                writer.write("Daftar Belanja:\n");
                for (OrderItem orderItem : cart.getOrderList()) {
                    Product product = orderItem.getProduct();
                    writer.write(product.getNama() + "  " + orderItem.getQuantity() + "kg  " + orderItem.getFinalPrice() + "\n");
                }
                writer.write("Total Perbelanjaan: " + cart.getTotalPrice() + "\n");
                writer.write("=============================\n");
            }
            writer.close();
            System.out.println("Struk berhasil dicetak: Silakan lihat file 'Struk.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    /**
     * method untuk mencari produk
     * @param name
     * @return
     */
    public Product findProduct(String name) {
        for (Product product : products) {
            if (product.getNama().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * method untuk mencari nama customer
     * @param name
     * @return
     */
    public Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }

}