package com.example.tugasfaishal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tugasfaishal.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TebakYuk.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("PROGRAMMING");
        insertCategory(c1);
        Category c2 = new Category("BAHASA INDONESIA");
        insertCategory(c2);
        Category c3 = new Category("SEJARAH");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Apakah Editor Untuk Membuat Andoid APP",
                "Microsoft Word", "Microsoft Excel", "Android Studio", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q1);
        Question q2 = new Question("HTML singkatan dari",
                "Hyper Text LEague", "HyperText Markup Language ", "Hiper Tex Markup", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q2);
        Question q3 = new Question(" Pseudocode yang di gunakan pada penulisan algoritma berupa",
                "Bahasa Pemrograman", "Bahasa Puitis", "Bahasa Inggris", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q3);
        Question q4 = new Question("Apa kepanjangan ari USES",
                "Unit secure", "Unit system", "Up software", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q4);
        Question q5 = new Question("Istilah Perulangan dalam pemrograman pascal dikenal dengan?",
                "Again", "Replay", "Looping", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q5);
        Question q6 = new Question("Tipe bilangan bulat dalam bahasa pascal dikenal sebagai",
                "Integer", "Byte", "Char", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q6);
        Question q7 = new Question("Tipe bilangan bulat dalam bahasa pascal dikenal sebagi",
                "Integer", "Byte", "Char", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q7);
        Question q8 = new Question("Perintah untuk menutup program dalam pascal adalah?",
                "End.", "Finish", "End;", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q8);
        Question q9 = new Question("Siapakah yang pertama kali menemukan bahasa c++",
                "Nikleus Wirth", "Dennie Ritchie", "Bjame Stroustrup", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q9);
        Question q10 = new Question("Yang bukan termasuk bahasa pemrograman adalah?",
                "C++", "Latin", "Pascal", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q10);

        Question q11 = new Question("Apakah fungsi dari tanda {} pada c++?",
                "Memblok Program", "Memberikan komentar", "Mengeksekusi program", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q11);
        Question q12 = new Question("Apakah fungsi dari frintf?",
                "Menampilkan variabel", "Menampilkan tulisan ", "Menghapus kalimat", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q12);
        Question q13 = new Question("  Apakah fungsi dari getche?",
                "Membaca spasi pada program", "Membuat program berjalan", "Menampilkan karakter tanpa menekan tombol", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q13);
        Question q14 = new Question("Bentuk dari suatu statment IF berada di dalam lingkungan statmen IF yang lainnya disebut...",
                "IF tunggal", "IF bersarang", "IF bercabang", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q14);
        Question q15 = new Question("Method yang digunakan untuk membandingkan dua buah data string adalah ",
                "length()", "concat()", "equals()", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q15);
        Question q16 = new Question("Suatu method yang dapat dijalankan otomatis pada saat object dari class dibuat, dikenal dengan ",
                "Inheritance", "Constructor", "Initializer", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q16);
        Question q17 = new Question("Increase atau Incremen adalah..",
                "Proses perkalian satu", "Proses pengurangan satu", "Proses penambahan satu", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q17);
        Question q18 = new Question("Perintah untuk menutup program dalam pascal adalah?",
                "End.", "Finish", "End;", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q18);
        Question q19 = new Question("Fungsi dari perintah Clrscr adalah...",
                "Membersihkan tulisan", "Menahan tampilan", "Membersihkan layar", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q19);
        Question q20 = new Question("Yang termasuk ke dalam jenis algoritma percabangan adalah...",
                "Main", "If else", "Repeat Until", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q20);

        Question q21 = new Question("Apabila a=5, b=10, maka jika diberikan instruksi a=b; b=a akan mengakibatkan..",
                "a=0, b=0", "a=0, b=10", "a=10, b=10", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q21);
        Question q22 = new Question("Penggunaan aritmatika yang benar yaitu...",
                "while(i<=3);", "t=t+angka;", "for(x=1,x<=10,X++);", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q22);
        Question q23 = new Question(" Pernyataan a = 7 % 4 akan menghasilkan a = ...",
                "2", "1", "3", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q23);
        Question q24 = new Question("Diantara perintah untuk mencetak berikut, yang benar adalah …",
                "System.out.println(*Mid Java*) ", "System.out.println(“Mid Java”)", "System.out.println('Mid Java') ", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q24);
        Question q25 = new Question("Diantara pernyataan berikut, penulisan sintaks BbufferredReader yang benar adalah?",
                "int data =BufferedReader = new BufferedReader(new InputStreamReader(System.in)).Readline( ).intValue( );", "int data =BufferedReader = new BufferedReader(new input Stream Reader(System.in)).Readline( ).intValue( );", "int data = (new Integer( (new BufferedReader (new InputStreamReader (System.in))).readLine()).intValue());", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q25);
        Question q26 = new Question("Dibawah ini cara penuliasan  program pointer yang benar adalah..",
                "P=&a;", "P=+a;", "P=+alamat;", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q26);
        Question q27 = new Question("++ dan -- merupakan operator...",
                "Operator Logika", "Operator Aritmatika", "Operator Increment dan Decrement", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q27);
        Question q28 = new Question("Istilah untuk melindungi data dari usaha modofikasi, perusakan dan penggandaan data oleh pihak yang tidak berwenang adalah ..",
                "Constructor", "Encapsulation", "Polymorphism", 2,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q28);
        Question q29 = new Question("Yang tidak termasuk perintah pada stdlib.h adalah..",
                "Power", "Pow", "Atof();", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q29);
        Question q30 = new Question("int All;\n" +
                "int a=2, b=3, c=6, d=5;\n" +
                "All = b * d - c / a + b;\n" +
                "System.out.print (“All:”+All);",
                "Error", "ALL:4", "ALL:9", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q30);

        //BAHASA INDONESIA

        Question q31 = new Question("Simpulan paragraph deduktif tersebut adalah..",
                "Bahasa merupakan media fisik", "Bahasa bukan hanya digambarkan sebagai kerangka bunyi", "Merupakan formalisasi media", 2,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q31);
        Question q32 = new Question("Teks negosiasi juga bisa disebut sebagai teks yang didalamnya berisi proses untuk mencapai suatu perjanjian atau kesepakatan antara....",
                "Pembelian dan penjualan", "Kedua belah pihak ", "Perorangan", 2,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q32);
        Question q33 = new Question("  Biografi dapat mengisahkan tentang kehidupan seorang tokoh penting atau terkenal maupun tidak terkenal. Namun kebanyakan biografi bercerita tentang...",
                "Tokoh Sejarah", "Kehidupan pribadi penulis", "Kehidupan nyata tokoh", 1,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q33);
        Question q34 = new Question("Puisi merupakan karya sastra hasil ungkapan pemikiran dan perasaan manusia yang bahasanya terikat oleh hal-hal berikut, kecuali...",
                "Irama", "Ragam", "Rima", 2,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q34);
        Question q35 = new Question(" berikut ini termasuk jenis-jenis dari puisi lama, kecuali...",
                "Pantun", "Seloka", "himne", 3,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q35);
        Question q36 = new Question(" Biografi yang isinya menceritakan tentang tokoh-tokoh yang berkecimpung pada dunia politik. Jenis biografi yang dimaksud adalah...",
                "Biografi Politik", "Biografi subsidi", "Biografi tokoh idola", 1,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q36);
        Question q37 = new Question("Puisi berjudul “aku“ merupakan karya dari..",
                "Chairil Anwal", "Acep Zamzam", "Goenawan Muhammad", 1,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q37);
        Question q38 = new Question("Suatu karya puisi yang baik memiliki makna yang mendalam. Makna diungkapkan dengan memadatkan berbagai..",
                "Bahasa kias", "Kata", "Unsur bahasa", 3,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q38);
        Question q39 = new Question("Sebelum debat dimulai, harus menentukan topik yang akan diperdebatkan. Topik debat harus dapat....",
                "dianalisis", "dipertanyakan", "dipertentangkan", 3,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q39);
        Question q40 = new Question("Kegiatan yang dilakukan untuk mempeebaiki teks yang telah ada. Pernyataan tersebut merupakan pengertian dari....",
                "Mengevaluasi teks", "Menilai teks", "Menelaah teks", 1,
                Question.DIFFICULTY_EASY, Category.INDONESIA);
        insertQuestion(q40);

        Question q41 = new Question("Simpulan paragraph deduktif tersebut adalah…",
                "Bahasa bukan hanya digambarkan sebagai kerangka bunyi", "Bahasa didefinisikan sebagai substansi", "Bahasa merupakam media fisik", 1,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q41);
        Question q42 = new Question("Yang merupakan bentuk informasi non verbal, kecuali….",
                "Gambar", "Tabel", "Bagan", 2,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q42);
        Question q43 = new Question("Bagi masyarakat Indonesia,bahasa Indonesia resmi dipakai dalam deretan berikut,kecuali…",
                "Bahasa pengajaran", "Bahasa Puitis", "Bahasa iklan", 1,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q43);
        Question q44 = new Question("Bagaimana cara kita menyimpulkan sebuah informasi ke dalam sebuah paragraph induktif..",
                "Dengan cara membaca paragraph secara keseluruhan", "Dengan mengedepankan ide penjelasan diakhir", "Dengan membaca teks secara teliti", 2,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q44);
        Question q45 = new Question("Apakah kelebihan dari informasi verbal?",
                "Menggunakan kata hubung yang begitu rumit.", "Bentuk penyajiannya yaitu umum khusus.", "Menggunakan kalimat kalimat informatif yang dianggap lebih mudah dimenegerti banyak orang.", 3,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q45);
        Question q46 = new Question("Dibawah ini yang tidak termauk ke dalam faktor kedudukan bahasa Indonesia adalah…",
                "Sebagai arus urbanisasi", "Susatra", "Jumlah penutur nya", 1,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q46);
        Question q47 = new Question("Dibawah ini yang merpakan pengertian dari informasi verbal adalah.",
                "Informasi yang meliputi informasi lisan dan tulis", "Informasi yang berasal dari sebuah media berita", " Informasi yang didapat dari hasil observasi", 1,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q47);
        Question q48 = new Question(" Manakah yang bukan fungsi bahasa Indonesia?",
                "Alat pemersatu", "Pembawa kewibawaan", "Sebagai dominasi bangsa", 3,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q48);
        Question q49 = new Question("Kalimat dalam teks prosedur tidak membingungkan dan mudah diikuti berarti kalimat tersebut ….",
                "Baku", "Jelas", "Logis", 3,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q49);
        Question q50 = new Question("Berikut yang bukan merupakan contoh dari konjungsi temporal adalah …",
                "Apabila", "Mengapa", "Kenapa", 2,
                Question.DIFFICULTY_MEDIUM, Category.INDONESIA);
        insertQuestion(q50);

        Question q51 = new Question("Di bawah ini termasuk pola menarik kesimpulan kecuali …",
                "Analogi", "Kronologi", "Akibat", 2,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q51);
        Question q52 = new Question("Di bawah ini adalah pihak-pihak yang terlibat dalam diskusi atau seminar, kecuali",
                "Moderator", "Ketua ", "Notulis", 2,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q52);
        Question q53 = new Question("Membuat surat lamaran yang baik dan benar harus ditinjau dari segi …",
                "kalimat – kata", "EYD", "hal – struktur", 1,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q53);
        Question q54 = new Question(" Berikut yang bukan persyaratan menulis di media cetak adalah …",
                "Bersifat aktual dan faktual", "Mengandung materi yang bersifat promosi komersial", "Tidak menyerang pribadi seseorang", 2,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q54);
        Question q55 = new Question("Langkah yang tepat untuk meyakinkan pendengar atas pendapat seseorang pembicara adalah …",
                "Tujuan jelas, berwajah serius, berterus terang.", "Tujuan jelas, membangkitkan semangat, tanpa teks.", "Tujuan jelas, mengemukakan fakta, tidak berbelit-belit (satu tema)", 3,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q55);
        Question q56 = new Question(" Bahasa laporan hendaknya tidak memenuhi kriteria berikut …",
                "logis", "baku", "Bahasa yang rancau", 3,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q56);
        Question q57 = new Question("Dalam kegiatan evaluasi sebuah laporan, hal yang harus diperhatikan adalah …",
                "keaktualan serta kebenaran dan data yang disajikan", " selalu curiga atas isi laporan", " mengeluarkan pendapat subjektif", 1,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q57);
        Question q58 = new Question("Kalimat berikut yang memiliki keterangan waktu adalah …",
                "Suci menyerupai ibunya.", " Ridwan tertidur di meja.", "Ibu pergi ke pasar tadi pagi.", 3,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q58);
        Question q59 = new Question("Fakta dalam teks tersebutkan yang tepat adalah …",
                " Semburan liar itu sama dengan semburan sebelumnya.", "Semburan liar itu akan mengecil dengan sedirinya.", "Semburan lumpur baru merupakan semburan ke-59", 3,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q59);
        Question q60 = new Question("Opini dalam teks tersebut adalah …",
                "Semburan liar itu merupakan semburan yang ke-59.", "Semburan liar itu tidak berbahaya dan akan mengecil dengan sendirinya.", "Hari ini ke dalam lubang asal semburan dimasukkan 100 kilogram semen.", 2,
                Question.DIFFICULTY_HARD, Category.INDONESIA);
        insertQuestion(q60);

        //SEJARAH

        Question q61 = new Question("Pada zaman ini hidup binatang-binatang besar (dinosaourus), seperti brontosaurus dan tyrrannosaurus. Zaman yang dimaksud adalah…",
                "Zaman Arkaekum", "Zaman Megalitikum", "Zaman Mesozoikum", 3,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q61);
        Question q62 = new Question("Menurut ahli geologi, pembagian umur bumi yang paling tua adalah zaman..",
                "Tersier", "Arkaekum", "Palaezoikum", 2,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q62);
        Question q63 = new Question("Berdasarkan penelitian geologi, zaman kuarter merupakan zaman terpenting bagi kita karena…",
                "Muncul binatang menyusui", "Ada tanda-tanda kehidupan", "Mulai ada manusia purba", 3,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q63);
        Question q64 = new Question("Zaman yang ditandai dengan menurunya suhu bumi secara drastis sehingga terbentuk hamparan es yang sangat tebal di daerah kutub adalah zaman..",
                "Trias", "Tersier", "Jura", 2,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q64);
        Question q65 = new Question("Ilmuwan yang berhasil menemukan fosil Pithecanthropus erectus di daerah Trinil, Ngawi, Jawa Timur adalah…",
                "Eugene Dubois", "Ter Haar", "Oppernoorth", 3,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q65);
        Question q66 = new Question("Seorang ilmuwan yang menemukan fosil Meganthropus paleojavaincus di Sangiran adalah…",
                "Von Koenisgswald", "Eugene Dubois", "Ter Haar", 1,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q66);
        Question q67 = new Question("Homo soloensis dan Homo wajakensis adalah pendukung peradaban zaman…",
                "Logam", "Neolitikum", "Paleolitikum", 3,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q67);
        Question q68 = new Question("Revolusi kebudayaan yang terjadi pada zaman Neolitikum di Indonesia adalah…",
                "Sudah mengenal teknologi logam", "Sudah mengenal bercocok tanam", "Sudah mengenal perdagangan;", 2,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q68);
        Question q69 = new Question("Salah satu peralatan manusia purba dari zaman Paleolitikum adalah…",
                "Moko", "Chopper", "Nekara", 2,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q69);
        Question q70 = new Question("Berikut ini ciri-ciri peradaban zaman Neolitikum, kecuali…",
                "Menggunakan kapak persegi", "Menggunakan peralatan gerabah", "Menggunakan peralatan perunggu", 3,
                Question.DIFFICULTY_EASY, Category.SEJARAH);
        insertQuestion(q70);

        Question q71 = new Question("Untuk melawan kekuatan Belanda, Pangeran Diponegoro menggunaankan taktik yang disebut…",
                "Taktik adu domba", "Perang puputan", "Perang gerilya", 3,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q71);
        Question q72 = new Question("Perlawanan rakyat Singaparna, Jawa Barat melawan Jepang dipimpin oleh…",
                "K.H Madriyas", "K.H Zaenal Mustafa", "Abdul Jalil", 2,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q72);
        Question q73 = new Question("K.H Zaenal Mustafa adalah tokoh perjuangan melawan penjajah yang berasal dari…",
                "Jawa Barat", "Maluku", "Jepara", 1,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q73);
        Question q74 = new Question("Pemberontakan Teuku Hamid terjadi di…",
                "Ambon, Maluku", "Meurudu, Aceh", "Blitar, Jawa Timur", 2,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q74);
        Question q75 = new Question("Pattimura gugur pada tahun",
                "1818", "1819", "1817", 3,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q75);
        Question q76 = new Question("Oleh Fatahillah, nama Sunda Kelapa diganti nama menjadi…",
                "Jayakarta", "Banten", "Batavia", 1,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q76);
        Question q77 = new Question("Perlawanan Peta di Blitar untuk melawan Jepang dipimpin oleh",
                "Ahmad Yani", "Supriyadi", "Sudirman", 2,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q77);
        Question q78 = new Question("Taktik perang Belanda yang berhasil membuat Teuku Umar gugur adalah…",
                "Benteng stelsel", "Perang geriliya", "Taktik marsose", 3,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q78);
        Question q79 = new Question("alah satu tokoh pejuang yang membantu Pangeran Diponegoro dalam Perang Diponegoro adalah…",
                "Kiai Bakar", "Kiai Mojo", "Fatahillah", 2,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q79);
        Question q80 = new Question("Tuanku Imam Bonjol berhasil ditangkap oleh Belanda pada tanggal…",
                "28 Oktober 1836", "28 Oktober 1837", "28 Oktober 1840", 2,
                Question.DIFFICULTY_MEDIUM, Category.SEJARAH);
        insertQuestion(q80);

        Question q81 = new Question(" Pimpinan dari Gerakan DI/TII Jawa Tengah, saat itu menjabat sebagai .",
                "Komandan Laskar Hisbullah di front Tulangan, Sidoarjo, dan Mojokerto", "Microsoft Excel", "Android Studio", 3,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q81);
        Question q82 = new Question("Latar belakang terjadinya pemberontakan Andi Azis adalah..",
                "Menolak masuknya pasaukan APRIS", " Tidak menyetujui Indonesia timur bergabung ke NKRI", "Membentuk negara islam", 2,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q82);
        Question q83 = new Question("Tokoh yang pertama kali bercita-cita ingin mendirikan Negara Islam Indonesia ialah ...",
                "Amir Fatah", "Kahar Mudzakar", "Kartosuwiryo", 1,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q83);
        Question q84 = new Question("Pemberontakan Kahar Mudzakar di Sulawesi Selatan disebabkan karena ..",
                "Memperjuangkan agar KGSS dimasukkan dalam APRIS", "Menentang pemerintah RIS", "Tidak Puasa dengan Pemerintah RI", 3,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q84);
        Question q85 = new Question(" Pemberontakan PKI Madiun merupakan puncak ketidakpuasan salah seorang tokoh oposan setelah jatuh dari Kabinet, ialah ...",
                "Muso", "Tan MAlaka", "Amir Syarifudin", 3,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q85);
        Question q86 = new Question("Pemberontakan DI/TII terjadi dibeberapa provinsi, kecuali .",
                "Sumatra Utara", "Jawa Barat", "Jawa tengah", 1,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q86);
        Question q87 = new Question("Penculikan dan pembunuhan dalam Peristiwa G-30-S/PKI membuktikan bahwa PKI adalah .",
                "Sangat biadab", "Pengkhianat", "Berbahaya", 1,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q87);
        Question q88 = new Question("Perintah untuk menutup program dalam pascal adalah?",
                "End.", "Finish", "End;", 3,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q88);
        Question q89 = new Question("Gerakan yang didalangi oleh golongan kolonialisme Belanda yang ingin mengamankan kepentingan ekonominya di Indonesia adalah ...",
                "RMS", "Andi Azis", "APRA", 3,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q89);
        Question q90 = new Question("Pimpinan dari Gerakan DI/TII Jawa Tengah, saat itu menjabat sebagai .",
                "Komandan Laskar Madura dan Surabaya ", "Komandan LAskar Hisbullah di fron Tukangan, Sidoarjo dan Mojokerto", "Komandan Laskar Kalimantan Selatan", 2,
                Question.DIFFICULTY_HARD, Category.SEJARAH);
        insertQuestion(q90);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}