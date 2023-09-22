package com.example.test2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WayFindingFragment extends Fragment {
    private String mota;
    private String hinh1;

        private List<SearchData> countryList;
    private List<SearchData> countryList2;
        private TextView textView,textView1,textView2,textView3;
        private TextView text1, text2, text3, text4;
        private ImageView hinh;
        private AutoCompleteTextView o;
        private ImageButton imgbtn, clearButton,clearButton2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.wayfinding_fragment, null);

            fillCountryList();
            fillCountryList2();
            AutoCompleteTextView editText = view.findViewById(R.id.actv);
            AutoCompleteTextView editText2 = view.findViewById(R.id.actv2);
            SearchAdapter adapter = new SearchAdapter(getContext(),countryList);
            SearchAdapter adapter2 = new SearchAdapter(getContext(),countryList2);
            editText.setAdapter(adapter);
            editText2.setAdapter(adapter2);
            textView = (TextView) view.findViewById(R.id.chiduong);
            textView1 = (TextView) view.findViewById(R.id.mota);

            hinh= (ImageView) view.findViewById(R.id.hình2);
            imgbtn = (ImageButton) view.findViewById(R.id.search_btn);
            clearButton = (ImageButton) view.findViewById(R.id.clear_btn);
            clearButton2 = (ImageButton) view.findViewById(R.id.clear_btn2);

            text1 = (TextView) view.findViewById(R.id.chiduong0);
            text2 = (TextView) view.findViewById(R.id.mota0);


//        editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN)
//                        && (keyCode == KeyEvent.KEYCODE_ENTER)
//                ) {
//                    String e=editText.getText().toString();
//                    for(CountryItem item :countryList){
//                        String f=item.getSearch().toString();
//                    if(e.equalsIgnoreCase(f)==true ){
//                    textView.setText(item.getMaphong());
//                    textView1.setText(item.getTenphong());
//                    textView2.setText(item.getTang());
//                    textView3.setText(item.getToa());
//                    hinh.setImageResource(item.getHinh());
//                        Toast.makeText(getActivity(), editText.getText(), Toast.LENGTH_SHORT).show();
//                    break;
//                   }
//                }
//                }
//                return false;
//            }
//        });
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText.setText("");
                }
            });
            clearButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editText2.setText("");
                }
            });

            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String e=editText.getText().toString().trim().toLowerCase(Locale.ROOT);
                    String e2=editText2.getText().toString().trim().toLowerCase(Locale.ROOT);
                    String toabd="",toadd="",tangbd="",tangdd="",tenphongbd="",tenphongdd="",maphongbd="",maphongdd="",chiduongdi="",toakodaubd="",toakodaudd="";
                    for(SearchData item :countryList){
                        String f=item.getSearch().trim().toLowerCase(Locale.ROOT);
                        if(e.length()==f.length()) {
                            if (e.equalsIgnoreCase(f) == true) {
                                toabd=item.getToa();
                                tangbd=item.getTang();
                                tenphongbd=item.getTenphong();
                                maphongbd=item.getMaphong();
                                toakodaubd=item.getToakodau();
//                                textView.setText(toabd);
//                                text1.setVisibility(View.VISIBLE);
                                break;
                            }
                        }

                    }
                    for(SearchData item :countryList2){
                        String f2=item.getSearch().trim().toLowerCase(Locale.ROOT);
                        if(e2.length()==f2.length()) {
                            if (e2.equalsIgnoreCase(f2) == true) {
                                toadd=item.getToa();
                                tangdd=item.getTang();
                                tenphongdd=item.getTenphong();
                                maphongdd=item.getMaphong();
                                toakodaudd=item.getToakodau();
//                                textView1.setText(toadd);
//                                text2.setVisibility(View.VISIBLE);

//                                DocumentReference docRef = db.collection("DanhSach").document(toadd).collection(tangdd).document(maphongdd);
//                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                        if (task.isSuccessful()) {
//                                            DocumentSnapshot document = task.getResult();
//                                            if (document.exists()) {
//                                                Log.d(TAG, "DocumentSnapshot data: " + document.get("HinhAnh"));
//                                                String a= (String) document.get("HinhAnh");
//                                                if(a.equalsIgnoreCase("")==false) {
//                                                    Picasso.with(getContext()).load((String) document.get("HinhAnh")).into(hinh);
//                                                                                                        hinh.setImageResource(item.getHinh());
//
//                                                }else {
////                                                    hinh.setImageResource(item.getHinh());
//                                                    hinh.setImageResource(R.drawable.ic_home);
//                                                }
//                                            } else {
//                                                Log.d(TAG, "No such document");
//                                            }
//                                        } else {
//                                            Log.d(TAG, "get failed with ", task.getException());
//                                        }
//                                    }
//                                });
                                break;
                            }
                        }

                    }
                    Log.d(TAG, "DocumentSnapshot data: " + toakodaudd + " "+tangdd+  " "+ maphongdd);
                    textView.setText(chiduong(toabd,toadd,tangbd,tangdd,tenphongbd,tenphongdd,maphongbd,maphongdd));
                    text1.setVisibility(View.VISIBLE);
                    DocumentReference docRef = db.collection("DanhSach").document(toakodaudd).collection(tangdd).document(maphongdd);
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    mota = (String) document.get("MoTa");

                                    mota.replaceAll("\n", " \n ");
                                    mota.replaceFirst("\n", " \n ");
                                    Log.d(TAG, "DocumentSnapshot data: " + document.get("MoTa"));
                                    textView1.setText(mota);
                                    text2.setVisibility(View.VISIBLE);


                                    Log.d(TAG, "DocumentSnapshot data: " + document.get("HinhAnh"));
                                    String a= (String) document.get("HinhAnh");
                                    if(a.equalsIgnoreCase("")==false) {
                                        Picasso.with(getContext()).load((String) document.get("HinhAnh")).into(hinh);
                                        hinh.setVisibility(View.VISIBLE);

                                    }else {
                                        hinh.setImageResource(R.drawable.ic_home);
                                    }
                                } else {
                                    Log.d(TAG, "No such document");
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });


                }
            });
            return view;
        }


    private void fillCountryList() {
        /**A*/
        {
            /**Tầng Trệt*/
            countryList = new ArrayList<>();
            countryList.add(new SearchData("A.01_Văn phòng khoa vật lý - vật lý kỹ thuật", R.drawable.ic_home, "01", "Văn phòng khoa vật lý - vật lý kỹ thuật", "Trệt", "A","ToaA"));
            countryList.add(new SearchData("A.02_Phòng công tác sinh viên", R.drawable.ic_home, "02", "Phòng công tác sinh viên", "Trệt", "A","ToaA"));
            countryList.add(new SearchData("A.03_Phòng phó hiệu trưởng", R.drawable.ic_home, "03", "Phòng phó hiệu trưởng", "Trệt", "A","ToaA"));
            countryList.add(new SearchData("A.04_Phòng quản trị thiết bị", R.drawable.ic_home, "04", "Phòng quản trị thiết bị", "Trệt", "A","ToaA"));
            countryList.add(new SearchData("BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", R.drawable.ic_home, "", "BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", "Trệt", "A","ToaA"));

            /**Tầng 1*/
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "", "PTN. BM. Hóa hữu cơ","1","A","ToaA"));
            countryList.add(new SearchData("BM. Hóa hữu cơ", R.drawable.ic_home, "", "BM. Hóa hữu cơ","1","A","ToaA"));
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "", "PTN. BM. Hóa hữu cơ","1","A","ToaA"));

            /**Tầng 2*/
            countryList.add(new SearchData("PTN. Hợp chất và hóa dược", R.drawable.ic_home, "", "PTN. Hợp chất và hóa dược","2","A","ToaA"));
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "", "PTN. BM. Hóa hữu cơ","2","A","ToaA"));

        }

        /**B*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("B.01_Phòng kế hoạch - tài chính", R.drawable.ic_home, "01", "Phòng kế hoạch - tài chính", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.02_Phòng đào tạo", R.drawable.ic_home, "02", "Phòng đào tạo", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.03", R.drawable.ic_home, "03", "", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.04_Phòng khảo thí và đảm bảo chất lượng", R.drawable.ic_home, "04", "Phòng khảo thí và đảm bảo chất lượng", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.05_Văn phòng hiệu trưởng", R.drawable.ic_home, "05", "Văn phòng hiệu trưởng", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.06_Phòng tổ chức - hành chính", R.drawable.ic_home, "06", "Phòng tổ chức - hành chính", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.07_Phòng khoa học và công nghệ", R.drawable.ic_home, "07", "Phòng khoa học và công nghệ", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.08_Phòng đào tạo sau đại học", R.drawable.ic_home, "08", "Phòng đào tạo sau đại học", "Trệt", "B","ToaB"));
            countryList.add(new SearchData("B.09", R.drawable.ic_home, "09", "", "Trệt", "B","ToaB"));

            /**Tầng 1*/
            countryList.add(new SearchData("B.11A", R.drawable.ic_home, "11A", "", "1", "B","ToaB"));
            countryList.add(new SearchData("B.11B", R.drawable.ic_home, "11B", "", "1", "B","ToaB"));
            countryList.add(new SearchData("B.13_BM. CNSH thực vật & chuyển hóa sinh học", R.drawable.ic_home, "13", "BM. CNSH thực vật & chuyển hóa sinh học", "1", "B","ToaB"));
            countryList.add(new SearchData("B.14", R.drawable.ic_home, "14", "", "1", "B","ToaB"));
            countryList.add(new SearchData("B.15", R.drawable.ic_home, "15", "", "1", "B","ToaB"));
            countryList.add(new SearchData("B.16_PTN. Phân tích trung tâm", R.drawable.ic_home, "16", "PTN. Phân tích trung tâm", "1", "B","ToaB"));
            countryList.add(new SearchData("B.17", R.drawable.ic_home, "17", "", "1", "B","ToaB"));
            countryList.add(new SearchData("B.18_PTN. CNSH phân tử A", R.drawable.ic_home, "18", "PTN. CNSH phân tử A", "1", "B","ToaB"));
            countryList.add(new SearchData("B.19_PTN. CNSH phân tử A", R.drawable.ic_home, "19", "PTN. CNSH phân tử A", "1", "B","ToaB"));

            /**Tầng 2*/
            countryList.add(new SearchData("WC cbnv nữ (tầng 2 tòa B)", R.drawable.ic_home, "", "WC cbnv nữ", "2", "B","ToaB"));
            countryList.add(new SearchData("B.21_BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", R.drawable.ic_home, "21", "BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", "2", "B","ToaB"));
            countryList.add(new SearchData("B.22_BM. Di truyền học\n" + "PTN. Di truyền", R.drawable.ic_home, "22", "BM. Di truyền học\n" + "PTN. Di truyền", "2", "B","ToaB"));
            countryList.add(new SearchData("B.23_BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", R.drawable.ic_home, "23", "BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", "2", "B","ToaB"));
            countryList.add(new SearchData("B.24_BM. Hóa lý\n" + "PTN. Hóa xúc tác", R.drawable.ic_home, "24", "BM. Hóa lý\n" + "PTN. Hóa xúc tác", "2", "B","ToaB"));
            countryList.add(new SearchData("B.25", R.drawable.ic_home, "25", "", "2", "B","ToaB"));
            countryList.add(new SearchData("B.26_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "26", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "2", "B","ToaB"));
            countryList.add(new SearchData("B.27_BM. Hóa phân tích", R.drawable.ic_home, "27", "BM. Hóa phân tích", "2", "B","ToaB"));
            countryList.add(new SearchData("B.28_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "28", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "2", "B","ToaB"));
            countryList.add(new SearchData("B.29", R.drawable.ic_home, "29", "", "2", "B","ToaB"));

            /**Tầng 3*/
            countryList.add(new SearchData("WC cbnv nam (tầng 3 tòa B)", R.drawable.ic_home, "", "WC cbnv nam", "3", "B","ToaB"));
            countryList.add(new SearchData("B.30_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", R.drawable.ic_home, "30", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", "3", "B","ToaB"));
            countryList.add(new SearchData("B.31_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", R.drawable.ic_home, "31", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", "3", "B","ToaB"));
            countryList.add(new SearchData("B.32_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", R.drawable.ic_home, "32", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", "3", "B","ToaB"));
            countryList.add(new SearchData("B.33_BM. Hải dương - khí tượng - thủy văn", R.drawable.ic_home, "33", "BM. Hải dương - khí tượng - thủy văn", "3", "B","ToaB"));
            countryList.add(new SearchData("B.34_BM. Vật lý địa cầu", R.drawable.ic_home, "34", "BM. Vật lý địa cầu", "3", "B","ToaB"));
            countryList.add(new SearchData("B.35_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", R.drawable.ic_home, "35", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", "3", "B","ToaB"));
            countryList.add(new SearchData("B.36_Khoa vật lý\n" + "Phòng máy tính", R.drawable.ic_home, "36", "Khoa vật lý\n" + "Phòng máy tính", "3", "B","ToaB"));
            countryList.add(new SearchData("B.37", R.drawable.ic_home, "37", "", "3", "B","ToaB"));
            countryList.add(new SearchData("B.38_BM. Vật lý lý thuyết", R.drawable.ic_home, "38", "BM. Vật lý lý thuyết", "3", "B","ToaB"));
            countryList.add(new SearchData("B.39_BM. Vật lý địa cầu", R.drawable.ic_home, "39", "BM. Vật lý địa cầu", "3", "B","ToaB"));

            /**Tầng 4*/
            countryList.add(new SearchData("B.40a_Phòng máy tính 1", R.drawable.ic_home, "40a", "Phòng máy tính 1", "4", "B","ToaB"));
            countryList.add(new SearchData("B.40", R.drawable.ic_home, "40", "", "4", "B","ToaB"));
            countryList.add(new SearchData("B.41_Phòng học", R.drawable.ic_home, "41", "Phòng học", "4", "B","ToaB"));
            countryList.add(new SearchData("B.42_Phòng học", R.drawable.ic_home, "42", "Phòng học", "4", "B","ToaB"));
            countryList.add(new SearchData("B.43_Phòng học", R.drawable.ic_home, "43", "Phòng học", "4", "B","ToaB"));
            countryList.add(new SearchData("B.44", R.drawable.ic_home, "44", "", "4", "B","ToaB"));
            countryList.add(new SearchData("B.45_Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", R.drawable.ic_home, "45", "Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", "4", "B","ToaB"));

        }

        /**C*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("C05_Phòng chuyên đề", R.drawable.ic_home, "05", "Phòng chuyên đề", "Trệt", "C","ToaC"));
            countryList.add(new SearchData("C04_PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển" + "PTN. Trầm tích học", R.drawable.ic_home, "04", "PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển", "Trệt", "C","ToaC"));
            countryList.add(new SearchData("C03_BM. Địa chất thủy văn - địa chất công trình", R.drawable.ic_home, "03", "BM. Địa chất thủy văn - địa chất công trình", "Trệt", "C","ToaC"));
            countryList.add(new SearchData("C02_BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", R.drawable.ic_home, "02", "BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", "Trệt", "C","ToaC"));
            countryList.add(new SearchData("C01_BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", R.drawable.ic_home, "01", "BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", "Trệt", "C","ToaC"));

            /**Tầng 1*/
            countryList.add(new SearchData("C15_Văn phòng khoa môi trường", R.drawable.ic_home, "15", "Văn phòng khoa môi trường","1","C","ToaC"));
            countryList.add(new SearchData("Khoa môi trường\n" + "Phòng chuyên đề", R.drawable.ic_home, "", "Khoa môi trường\n" + "Phòng chuyên đề","1","C","ToaC"));
            countryList.add(new SearchData("C14_PTN. Phân tích và kiểm soát ô nhiễm môi trường", R.drawable.ic_home, "14", "PTN. Phân tích và kiểm soát ô nhiễm môi trường","1","C","ToaC"));
            countryList.add(new SearchData("WC cbnv nữ (tầng 1 tòa C)", R.drawable.ic_home, "", "WC cbnv nữ","1","C","ToaC"));
            countryList.add(new SearchData("C12A_Văn phòng khoa địa chất", R.drawable.ic_home, "12A", "Văn phòng khoa địa chất","1","C","ToaC"));
            countryList.add(new SearchData("Khoa địa chất", R.drawable.ic_home, "", "Khoa địa chất","1","C","ToaC"));
            countryList.add(new SearchData("C12_BM. Địa chất dầu khí", R.drawable.ic_home, "12", "BM. Địa chất dầu khí","1","C","ToaC"));
            countryList.add(new SearchData("C11_PTN. Địa hóa và địa chất môi trường", R.drawable.ic_home, "11", "PTN. Địa hóa và địa chất môi trường","1","C","ToaC"));


            /**Tầng 2*/
            countryList.add(new SearchData("C25_BM. Quản lý và tin học môi trường", R.drawable.ic_home, "25", "BM. Quản lý và tin học môi trường","2","C","ToaC"));
            countryList.add(new SearchData("C24", R.drawable.ic_home, "24", "","2","C","ToaC"));
            countryList.add(new SearchData("C23A_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "23A", "Khoa CNTT \n" + "Phòng máy tính","2","C","ToaC"));
            countryList.add(new SearchData("C23B_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "23B", "Khoa CNTT\n" + "Phòng máy tính","2","C","ToaC"));
            countryList.add(new SearchData("C22", R.drawable.ic_home, "22", "","2","C","ToaC"));
            countryList.add(new SearchData("C21_PTN. Tinh thể - ngọc học", R.drawable.ic_home, "21", "PTN. Tinh thể - ngọc học","2","C","ToaC"));

            /**Tầng 3*/
            countryList.add(new SearchData("C34_BM. Khoa học môi trường", R.drawable.ic_home, "34", "BM. Khoa học môi trường","3","C","ToaC"));
            countryList.add(new SearchData("C34A_BM. Công nghệ môi trường", R.drawable.ic_home, "34A", "BM. Công nghệ môi trường","3","C","ToaC"));
            countryList.add(new SearchData("C33", R.drawable.ic_home, "33", "","3","C","ToaC"));
            countryList.add(new SearchData("WC nữ (tầng 3 tòa C)", R.drawable.ic_home, "", "WC nữ","3","C","ToaC"));
            countryList.add(new SearchData("C32B", R.drawable.ic_home, "32B", "","3","C","ToaC"));
            countryList.add(new SearchData("C32A", R.drawable.ic_home, "32A", "","3","C","ToaC"));
            countryList.add(new SearchData("C31", R.drawable.ic_home, "31", "","3","C","ToaC"));

            /**Tầng 4*/
            countryList.add(new SearchData("C44_Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán", R.drawable.ic_home, "44", "Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán","4","C","ToaC"));

            countryList.add(new SearchData("C43B", R.drawable.ic_home, "43B", "","4","C","ToaC"));
            countryList.add(new SearchData("C43A", R.drawable.ic_home, "43A", "","4","C","ToaC"));
            countryList.add(new SearchData("C42", R.drawable.ic_home, "42", "","4","C","ToaC"));
            countryList.add(new SearchData("C41", R.drawable.ic_home, "41", "","4","C","ToaC"));


        }

        /**D*/
        {
            /**Tầng Trệt*/
            countryList.add(new SearchData("Trung tâm khoa học và công nghệ sinh học", R.drawable.ic_home, "D01", "Trung tâm khoa học và công nghệ sinh học","Trệt", "D","ToaD"));

            /**Tầng 1*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("PTN. Thực vật_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "PTN. Thực vật", "Phòng học\n" + "Phòng thí nghiệm","1","D","ToaD"));
            countryList.add(new SearchData("PTN. Thực vật_Phòng nghiên cứu", R.drawable.ic_home, "PTN. Thực vật", "Phòng nghiên cứu","1","D","ToaD"));

            /**Tầng 2*/
            countryList.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "PTN. Động vật\n" + "PTN. Sinh học đại cương", "Phòng học\n" + "Phòng thí nghiệm","2","D","ToaD"));
            countryList.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng nghiên cứu", R.drawable.ic_home, "PTN. Động vật\n" + "PTN. Sinh học đại cương", "Phòng nghiên cứu","2","D","ToaD"));

        }

        /**E*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("Phòng khoa học và công nghệ", R.drawable.ic_home, "", "Phòng khoa học và công nghệ", "Trệt", "E","ToaE"));
            countryList.add(new SearchData("Phòng y tế", R.drawable.ic_home, "", "Phòng y tế", "Trệt", "E","ToaE"));
            countryList.add(new SearchData("BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", R.drawable.ic_home, "", "BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", "Trệt", "E","ToaE"));
            countryList.add(new SearchData("PTN. Vi sinh - Sinh môi", R.drawable.ic_home, "", "PTN. Vi sinh - Sinh môi", "Trệt", "E","ToaE"));

            /**Tầng 1*/
            countryList.add(new SearchData("E101C_Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học", R.drawable.ic_home, "101C", "Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học","1","E","ToaE"));
            countryList.add(new SearchData("E101A_Khoa ĐTVT\n" + "Phòng họp", R.drawable.ic_home, "101A", "Khoa ĐTVT\n" + "Phòng họp","1","E","ToaE"));
            countryList.add(new SearchData("E101B_PTN. DESLAB", R.drawable.ic_home, "101B", "PTN. DESLAB","1","E","ToaE"));
            countryList.add(new SearchData("WC nữ (tầng 1 tòa E)\n" + "VC - NLĐ", R.drawable.ic_home, "", "WC nữ\n" + "VC - NLĐ","1","E","ToaE"));
            countryList.add(new SearchData("E102_Khoa điện tử - viễn thông\n" + "Phòng máy tính", R.drawable.ic_home, "102", "Khoa điện tử - viễn thông\n" + "Phòng máy tính","1","E","ToaE"));
            countryList.add(new SearchData("E103A_BM. Máy tính - hệ thống nhúng", R.drawable.ic_home, "103A", "BM. Máy tính - hệ thống nhúng","1","E","ToaE"));
            countryList.add(new SearchData("E103B_PTN. Máy tính - hệ thống nhúng", R.drawable.ic_home, "103B", "PTN. Máy tính - hệ thống nhúng","1","E","ToaE"));
            countryList.add(new SearchData("E104_PTN. Viễn thông", R.drawable.ic_home, "104", "PTN. Viễn thông","1","E","ToaE"));
            countryList.add(new SearchData("PTN. Điện tử", R.drawable.ic_home, "", "PTN. Điện tử","1","E","ToaE"));
            countryList.add(new SearchData("BM. Điện tử", R.drawable.ic_home, "", "BM. Điện tử","1","E","ToaE"));
            countryList.add(new SearchData("E106_BM. Viễn thông và mạng", R.drawable.ic_home, "E106", "BM. Viễn thông và mạng","Tang1","E","ToaE"));
            countryList.add(new SearchData("E107_Văn phòng khoa điện tử - viễn thông", R.drawable.ic_home, "E107", "Văn phòng khoa điện tử - viễn thông" ,"Tang1","E","ToaE"));
            /**Tầng 2*/
            countryList.add(new SearchData("E201B", R.drawable.ic_home, "201B", "","2","E","ToaE"));
            countryList.add(new SearchData("E201A_BM. Giải tích", R.drawable.ic_home, "201A", "BM. Giải tích","2","E","ToaE"));
            countryList.add(new SearchData("WC nam (tầng 2 tòa E)", R.drawable.ic_home, "", "WC nam","2","E","ToaE"));
            countryList.add(new SearchData("E202B_Phòng máy vi tính", R.drawable.ic_home, "202B", "Phòng máy vi tính","2","E","ToaE"));
            countryList.add(new SearchData("E202A_BM. Ứng dụng tin học", R.drawable.ic_home, "202A", "BM. Ứng dụng tin học","2","E","ToaE"));
            countryList.add(new SearchData("E203_BM. Vật lý chất rắn", R.drawable.ic_home, "203", "BM. Vật lý chất rắn","2","E","ToaE"));
            countryList.add(new SearchData("E204", R.drawable.ic_home, "204", "","2","E","ToaE"));
            countryList.add(new SearchData("E205_BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng", R.drawable.ic_home, "205", "BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng","2","E","ToaE"));
            countryList.add(new SearchData("L2.P6", R.drawable.ic_home, "L2.P6", "","2","E","ToaE"));

            /**Tầng 3*/
            countryList.add(new SearchData("E301", R.drawable.ic_home, "301", "","3","E","ToaE"));
            countryList.add(new SearchData("WC nữ (tầng 3 tòa E)", R.drawable.ic_home, "", "WC nữ","3","E","ToaE"));
            countryList.add(new SearchData("E302", R.drawable.ic_home, "302", "","3","E","ToaE"));
            countryList.add(new SearchData("E303A_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử", R.drawable.ic_home, "303A", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử","3","E","ToaE"));
            countryList.add(new SearchData("E303B_Phòng thực tập điện tử", R.drawable.ic_home, "303B", "Phòng thực tập điện tử","3","E","ToaE"));
            countryList.add(new SearchData("E304_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học", R.drawable.ic_home, "304", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học","3","E","ToaE"));
            countryList.add(new SearchData("BM. Vật lý tin học\n" + "Phòng máy tính", R.drawable.ic_home, "", "BM. Vật lý tin học\n" + "Phòng máy tính","3","E","ToaE"));
            countryList.add(new SearchData("E305_PTN. Thiết kế vi mạch và hệ thống nhúng", R.drawable.ic_home, "305", "PTN. Thiết kế vi mạch và hệ thống nhúng","3","E","ToaE"));
            countryList.add(new SearchData("E306_BM. Vật lý tin học\n" + "PTN chuyên đề", R.drawable.ic_home, "306", "BM. Vật lý tin học\n" + "PTN chuyên đề","3","E","ToaE"));

            /**Tầng 4*/
            countryList.add(new SearchData("E401", R.drawable.ic_home, "401", "","4","E","ToaE"));
            countryList.add(new SearchData("WC nam (tầng 4 tòa E)", R.drawable.ic_home, "", "WC nam","4","E","ToaE"));
            countryList.add(new SearchData("E402", R.drawable.ic_home, "402", "","4","E","ToaE"));
            countryList.add(new SearchData("E403", R.drawable.ic_home, "403", "","4","E","ToaE"));
            countryList.add(new SearchData("E404", R.drawable.ic_home, "404", "","4","E","ToaE"));
            countryList.add(new SearchData("E405", R.drawable.ic_home, "405", "","4","E","ToaE"));
            countryList.add(new SearchData("E406", R.drawable.ic_home, "406", "","4","E","ToaE"));


        }

        /**F*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("WC nữ (tầng trệt tòa F)", R.drawable.ic_home, "", "WC nữ","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.01_Phòng tổ chức hành chính", R.drawable.ic_home, "01", "Phòng tổ chức hành chính","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.02_Phòng thông tin - truyền thông", R.drawable.ic_home, "02", "Phòng thông tin - truyền thông","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.03_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "03", "BM. Di truyền\n" + "PTN. Sinh học phân tử","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.04_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "04", "BM. Di truyền\n" + "PTN. Sinh học phân tử","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.06_Văn phòng khoa sinh học - công nghệ sinh học", R.drawable.ic_home, "06", "Văn phòng khoa sinh học - công nghệ sinh học","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.07_Phòng kế hoạch - tài chính", R.drawable.ic_home, "07", "Phòng kế hoạch - tài chính","Trệt","F","ToaF"));
            countryList.add(new SearchData("WC cbvc nữ (tầng trệt tòa F)", R.drawable.ic_home, "", "WC cbvc nữ","Trệt","F","ToaF"));
            countryList.add(new SearchData("Tổ giảng đường", R.drawable.ic_home, "", "Tổ giảng đường","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.08_Văn phòng khoa toán - tin học", R.drawable.ic_home, "08", "Văn phòng khoa toán - tin học","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.09_Khoa toán - tin học", R.drawable.ic_home, "09", "Khoa toán - tin học","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.10_Trung tâm khoa học - toán học", R.drawable.ic_home, "10", "Trung tâm khoa học - toán học","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.11_BM. Cơ học", R.drawable.ic_home, "11", "BM. Cơ học","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.12_BM. Xác suất thống kê", R.drawable.ic_home, "12", "BM. Xác suất thống kê","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.13_BM. Tối ưu & hệ thống", R.drawable.ic_home, "13", "BM. Tối ưu & hệ thống","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.14_BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không", R.drawable.ic_home, "14", "BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.15_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "15", "PTN. Vật liệu kỹ thuật cao","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.16_PTN. Tổng hợp & phân tích vật liệu màng mỏng", R.drawable.ic_home, "16", "PTN. Tổng hợp & phân tích vật liệu màng mỏng","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.17_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "17", "PTN. Vật liệu kỹ thuật cao","Trệt","F","ToaF"));
            countryList.add(new SearchData("WC nam (tầng trệt tòa F)", R.drawable.ic_home, "", "WC nam","Trệt","F","ToaF"));
            countryList.add(new SearchData("F.18_PTN. Tổng hợp vật liệu màng mỏng", R.drawable.ic_home, "18", "PTN. Tổng hợp vật liệu màng mỏng","Trệt","F","ToaF"));

            /**Tầng 1*/

            countryList.add(new SearchData("WC nam (tầng 1 tòa F)", R.drawable.ic_home, "", "WC nam","1","F","ToaF"));
            countryList.add(new SearchData("F.100_BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường", R.drawable.ic_home, "100", "BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường","1","F","ToaF"));
            countryList.add(new SearchData("F.101", R.drawable.ic_home, "101", "","1","F","ToaF"));
            countryList.add(new SearchData("F.101A_Phòng quan hệ đối ngoại", R.drawable.ic_home, "101A", "Phòng quan hệ đối ngoại","1","F","ToaF"));
            countryList.add(new SearchData("F.101B_PTN. CNSH phân tử (B)", R.drawable.ic_home, "101B", "PTN. CNSH phân tử (B)","1","F","ToaF"));
            countryList.add(new SearchData("F.102_Phòng họp", R.drawable.ic_home, "102", "Phòng họp","1","F","ToaF"));
            countryList.add(new SearchData("Rest room", R.drawable.ic_home, "", "rest room","1","F","ToaF"));
            countryList.add(new SearchData("F.103_Văn phòng hội đồng trường", R.drawable.ic_home, "103", "Văn phòng hội đồng trường","1","F","ToaF"));
            countryList.add(new SearchData("F.104", R.drawable.ic_home, "104", "","1","F","ToaF"));
            countryList.add(new SearchData("F.105_Phòng quan hệ đối ngoại", R.drawable.ic_home, "105", "Phòng quan hệ đối ngoại","1","F","ToaF"));
            countryList.add(new SearchData("F.106A_Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh", R.drawable.ic_home, "106A", "Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh","1","F","ToaF"));
            countryList.add(new SearchData("F.106B_Văn phòng công đoàn", R.drawable.ic_home, "106B", "Văn phòng công đoàn","1","F","ToaF"));
            countryList.add(new SearchData("F.107_Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế", R.drawable.ic_home, "107", "Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế","1","F","ToaF"));
            countryList.add(new SearchData("F.108_Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên", R.drawable.ic_home, "108", "Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên","1","F","ToaF"));
            countryList.add(new SearchData("F.109_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "109", "Phòng máy\n" + "Khoa CNTT","1","F","ToaF"));
            countryList.add(new SearchData("F.110_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "110", "Phòng máy\n" + "Khoa CNTT","1","F","ToaF"));
            countryList.add(new SearchData("F.111", R.drawable.ic_home, "111", "","1","F","ToaF"));
            countryList.add(new SearchData("F.112A_Khoa khoa học và công nghệ vật liệu", R.drawable.ic_home, "112A", "Khoa khoa học và công nghệ vật liệu","1","F","ToaF"));
            countryList.add(new SearchData("F.112_BM. Sinh lý học và CNSH động vật", R.drawable.ic_home, "112", "BM. Sinh lý học và CNSH động vật","1","F","ToaF"));
            countryList.add(new SearchData("F.113_Văn phòng khoa Khoa khoa học và công nghệ vật  liệu", R.drawable.ic_home, "113", "Văn phòng khoa Khoa khoa học và công nghệ vật  liệu","1","F","ToaF"));

            /**Tầng 2*/
            countryList.add(new SearchData("Khoa môi trường - phòng đọc", R.drawable.ic_home, "", "Khoa môi trường - phòng đọc","2","F","ToaF"));
            countryList.add(new SearchData("F.200_Khoa môi trường\n" + "PTN. Tin học môi trường", R.drawable.ic_home, "200", "Khoa môi trường\n" + "PTN. Tin học môi trường","2","F","ToaF"));
            countryList.add(new SearchData("F.202", R.drawable.ic_home, "202", "","2","F","ToaF"));
            countryList.add(new SearchData("F.203", R.drawable.ic_home, "203", "","2","F","ToaF"));
            countryList.add(new SearchData("University council - office of the chair", R.drawable.ic_home, "", "University council - office of the chair","2","F","ToaF"));
            countryList.add(new SearchData("F205A", R.drawable.ic_home, "205A", "","2","F","ToaF"));
            countryList.add(new SearchData("F205B", R.drawable.ic_home, "205B", "","2","F","ToaF"));
            countryList.add(new SearchData("F.206A_BM. Tài chính định lượng", R.drawable.ic_home, "206A", "BM. Tài chính định lượng","2","F","ToaF"));
            countryList.add(new SearchData("F.206_BM. Giáo dục toán học", R.drawable.ic_home, "206", "BM. Giáo dục toán học","2","F","ToaF"));
            countryList.add(new SearchData("F.207", R.drawable.ic_home, "207", "","2","F","ToaF"));
            countryList.add(new SearchData("F.208_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "208", "Phòng máy\n" + "Khoa toán - tin học","2","F","ToaF"));
            countryList.add(new SearchData("F.209_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "209", "Phòng máy\n" + "Khoa toán - tin học","2","F","ToaF"));
            countryList.add(new SearchData("F.210_BM. Đại số", R.drawable.ic_home, "210", "BM. Đại số","2","F","ToaF"));
            countryList.add(new SearchData("F.211_PTN. Tổng hợp vật liệu polyme", R.drawable.ic_home, "211", "PTN. Tổng hợp vật liệu polyme","2","F","ToaF"));

            /**Tầng 3*/
            countryList.add(new SearchData("F.300", R.drawable.ic_home, "300", "","3","F","ToaF"));
            countryList.add(new SearchData("F.301_Phòng học", R.drawable.ic_home, "301", "Phòng học","3","F","ToaF"));
            countryList.add(new SearchData("F.302", R.drawable.ic_home, "302", "","3","F","ToaF"));
            countryList.add(new SearchData("F.303_Phòng học", R.drawable.ic_home, "303", "Phòng học","3","F","ToaF"));
            countryList.add(new SearchData("F.304_Phòng học", R.drawable.ic_home, "304", "Phòng học","3","F","ToaF"));
//                countryList.add(new SearchData("F.305_Phòng học", R.drawable.ic_home, "305", "Phòng học","3","F","ToaF"));
            countryList.add(new SearchData("BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh", R.drawable.ic_home, "", "BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh","3","F","ToaF"));
            countryList.add(new SearchData("F.305_PTN. Phân tích vật liệu", R.drawable.ic_home, "305", "PTN. Phân tích vật liệu","3","F","ToaF"));

            /**Tầng 4*/
            countryList.add(new SearchData("F.400_PTN. Phân tích vật liệu polyme", R.drawable.ic_home, "400", "PTN. Phân tích vật liệu polyme","4","F","ToaF"));


        }

        /**H*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("H2.2", R.drawable.ic_home, "2.2", "", "Trệt", "H","ToaH"));
            countryList.add(new SearchData("H2.1", R.drawable.ic_home, "2.1", "", "Trệt", "H","ToaH"));

            /**Tầng 1*/
            countryList.add(new SearchData("H2.3", R.drawable.ic_home, "2.3", "","1","H","ToaH"));

        }

        /**I*/
        {

            /**Tầng 1*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("Hội trường I", R.drawable.ic_home, "", "Hội trường I", "1", "I","ToaI"));
            countryList.add(new SearchData("I.12_Phòng họp", R.drawable.ic_home, "12", "Phòng họp", "1", "I","ToaI"));
            countryList.add(new SearchData("I.11_Phòng họp", R.drawable.ic_home, "11", "Phòng họp", "1", "I","ToaI"));
            countryList.add(new SearchData("WC (tầng 1 tòa I)", R.drawable.ic_home, "", "WC1","1","I","ToaI"));


            /**Tầng 2*/
            countryList.add(new SearchData("I.25_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "25", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","2","I","ToaI"));
            countryList.add(new SearchData("I.26", R.drawable.ic_home, "26", "","2","I","ToaI"));
            countryList.add(new SearchData("I.27_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "27", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","2","I","ToaI"));
            countryList.add(new SearchData("I.24", R.drawable.ic_home, "24", "","2","I","ToaI"));
            countryList.add(new SearchData("I.23", R.drawable.ic_home, "23", "","2","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 2 tòa I)", R.drawable.ic_home, "", "WC2","2","I","ToaI"));

            /**Tầng 3*/
            countryList.add(new SearchData("I.35_PTN. Hóa vô cơ chuyên ngành", R.drawable.ic_home, "35", "PTN. Hóa vô cơ chuyên ngành","3","I","ToaI"));
            countryList.add(new SearchData("I.36_BM. Hóa vô cơ và ứng dụng", R.drawable.ic_home, "36", "BM. Hóa vô cơ và ứng dụng","3","I","ToaI"));
            countryList.add(new SearchData("I.37_Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng", R.drawable.ic_home, "37", "Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng","3","I","ToaI"));
            countryList.add(new SearchData("I.38_Phòng hội thảo", R.drawable.ic_home, "38", "Phòng hội thảo","3","I","ToaI"));
            countryList.add(new SearchData("I.31", R.drawable.ic_home, "31", "","3","I","ToaI"));
            countryList.add(new SearchData("I.32", R.drawable.ic_home, "32", "","3","I","ToaI"));
            countryList.add(new SearchData("I.33", R.drawable.ic_home, "33", "","3","I","ToaI"));
            countryList.add(new SearchData("I.34", R.drawable.ic_home, "34", "","3","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 3 tòa I)", R.drawable.ic_home, "", "WC3","3","I","ToaI"));

            /**Tầng 4*/
            countryList.add(new SearchData("I.46_PTN. Hóa dược", R.drawable.ic_home, "46", "PTN. Hóa dược","4","I","ToaI"));
            countryList.add(new SearchData("I.47_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "47", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành","4","I","ToaI"));
            countryList.add(new SearchData("I.48_BM. Hóa phân tích", R.drawable.ic_home, "48", "BM. Hóa phân tích","4","I","ToaI"));
            countryList.add(new SearchData("I.44_APCS-404", R.drawable.ic_home, "44", "APCS-404","4","I","ToaI"));
            countryList.add(new SearchData("I.43_Văn phòng các chương trình đào tạo đặc biệt ngành CNTT", R.drawable.ic_home, "43", "Văn phòng các chương trình đào tạo đặc biệt ngành CNTT","4","I","ToaI"));
            countryList.add(new SearchData("I.42_APCS-402", R.drawable.ic_home, "42", "APCS-402","4","I","ToaI"));
            countryList.add(new SearchData("I.41_APCS-401", R.drawable.ic_home, "41", "APCS-401","4","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 4 tòa I)", R.drawable.ic_home, "", "WC4","4","I","ToaI"));


            /**Tầng 5*/
            countryList.add(new SearchData("I.56_PTN. Hóa lý ứng dụng", R.drawable.ic_home, "56", "PTN. Hóa lý ứng dụng","5","I","ToaI"));
            countryList.add(new SearchData("I.57_BM. Hóa lý\n" + "PTN. Hóa nano", R.drawable.ic_home, "57", "BM. Hóa lý\n" + "PTN. Hóa nano","5","I","ToaI"));
            countryList.add(new SearchData("I.58_BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ", R.drawable.ic_home, "58", "BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ","5","I","ToaI"));
            countryList.add(new SearchData("I.59_BM. Hóa dược", R.drawable.ic_home, "59", "BM. Hóa dược","5","I","ToaI"));
            countryList.add(new SearchData("I.59A_Phòng hội thảo", R.drawable.ic_home, "59A", "Phòng hội thảo","5","I","ToaI"));
            countryList.add(new SearchData("I.59B_Văn phòng khoa", R.drawable.ic_home, "59B", "Văn phòng khoa","5","I","ToaI"));
            countryList.add(new SearchData("I.55_Phòng họp", R.drawable.ic_home, "55", "Phòng họp","5","I","ToaI"));
            countryList.add(new SearchData("I.54_Văn phòng khoa CNTT", R.drawable.ic_home, "54", "Văn phòng khoa CNTT","5","I","ToaI"));
            countryList.add(new SearchData("I.53_Bộ phận giáo vụ\n" + "Trợ lý sinh viên", R.drawable.ic_home, "53", "Bộ phận giáo vụ\n" + "Trợ lý sinh viên","5","I","ToaI"));
            countryList.add(new SearchData("I.52_Phòng máy tính", R.drawable.ic_home, "52", "Phòng máy tính","5","I","ToaI"));
            countryList.add(new SearchData("I.51_Bộ phận kỹ thuật", R.drawable.ic_home, "51", "Bộ phận kỹ thuật","5","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 5 tòa I)", R.drawable.ic_home, "", "WC5","5","I","ToaI"));

            /**Tầng 6*/
            countryList.add(new SearchData("I.65_BM. Hóa học cao phân tử\n" + "Polyme 1", R.drawable.ic_home, "65", "BM. Hóa học cao phân tử\n" + "Polyme 1", "6", "I","ToaI"));
            countryList.add(new SearchData("I.66_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "66", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "6", "I","ToaI"));
            countryList.add(new SearchData("I.67_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "67", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "6", "I","ToaI"));
            countryList.add(new SearchData("I.68_BM. Hóa lý\n" + "PTN. Điện hóa", R.drawable.ic_home, "68", "BM. Hóa lý\n" + "PTN. Điện hóa", "6", "I","ToaI"));
            countryList.add(new SearchData("I.69_BM. Hóa học cao phân tử\n" + "PTN. Polyme 2" , R.drawable.ic_home, "69", "BM. Hóa học cao phân tử\n" + "PTN. Polyme 2", "6", "I","ToaI"));
            countryList.add(new SearchData("I.69A_Văn phòng bộ môn", R.drawable.ic_home, "69A", "Văn phòng bộ môn", "6", "I","ToaI"));
            countryList.add(new SearchData("I.64_PTN. Trí tuệ nhân tạo", R.drawable.ic_home, "64", "PTN. Trí tuệ nhân tạo", "6", "I","ToaI"));
            countryList.add(new SearchData("I.63_BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", R.drawable.ic_home, "63", "BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", "6", "I","ToaI"));
            countryList.add(new SearchData("I.62_Phòng máy tính", R.drawable.ic_home, "62", "Phòng máy tính", "6", "I","ToaI"));
            countryList.add(new SearchData("I.61_Phòng máy tính", R.drawable.ic_home, "61", "Phòng máy tính", "6", "I","ToaI"));
            countryList.add(new SearchData("WC (tầng 6 tòa I)", R.drawable.ic_home, "", "WC6","6","I","ToaI"));

            /**Tầng 7*/
            countryList.add(new SearchData("I.76_Phòng họp sinh viên", R.drawable.ic_home, "76", "Phòng họp sinh viên","7","I","ToaI"));
            countryList.add(new SearchData("I.77_PTN. Hóa tin", R.drawable.ic_home, "77", "PTN. Hóa tin","7","I","ToaI"));
            countryList.add(new SearchData("I.78_PTN. Hóa tin", R.drawable.ic_home, "78", "PTN. Hóa tin","7","I","ToaI"));
            countryList.add(new SearchData("I.79", R.drawable.ic_home, "79", "","7","I","ToaI"));
            countryList.add(new SearchData("I.79A", R.drawable.ic_home, "79A", "","7","I","ToaI"));
            countryList.add(new SearchData("I.75", R.drawable.ic_home, "75", "","7","I","ToaI"));
            countryList.add(new SearchData("I.74_BM. Mạng máy tính và viễn thông", R.drawable.ic_home, "74", "BM. Mạng máy tính và viễn thông","7","I","ToaI"));
            countryList.add(new SearchData("I.73_PTN. An ninh mạng", R.drawable.ic_home, "73", "PTN. An ninh mạng","7","I","ToaI"));
            countryList.add(new SearchData("I.72_BM. Thị giác máy tính và khoa học robot", R.drawable.ic_home, "72", "BM. Thị giác máy tính và khoa học robot","7","I","ToaI"));
            countryList.add(new SearchData("I.72A_Phòng họp", R.drawable.ic_home, "72A", "Phòng họp","7","I","ToaI"));
            countryList.add(new SearchData("I.71_Phòng máy tính", R.drawable.ic_home, "71", "Phòng máy tính","7","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 7 tòa I)", R.drawable.ic_home, "", "WC7","7","I","ToaI"));

            /**Tầng 8*/
            countryList.add(new SearchData("I.86_PTN. Hệ thống nhúng", R.drawable.ic_home, "86", "PTN. Hệ thống nhúng","8","I","ToaI"));
            countryList.add(new SearchData("I.87_PTN. Công nghệ phần mềm", R.drawable.ic_home, "87", "PTN. Công nghệ phần mềm","8","I","ToaI"));
            countryList.add(new SearchData("I.87A_PTN. Hệ thống di động & media", R.drawable.ic_home, "87A", "PTN. Hệ thống di động & media","8","I","ToaI"));
            countryList.add(new SearchData("I.88", R.drawable.ic_home, "88", "","8","I","ToaI"));
            countryList.add(new SearchData("I.89_Phòng vật lý tính toán", R.drawable.ic_home, "89", "Phòng vật lý tính toán","8","I","ToaI"));
            countryList.add(new SearchData("I.85_Phòng thông tin - truyền thông", R.drawable.ic_home, "85", "Phòng thông tin - truyền thông","8","I","ToaI"));
            countryList.add(new SearchData("I.84_BM. Hệ thống thông tin", R.drawable.ic_home, "84", "BM. Hệ thống thông tin","8","I","ToaI"));
            countryList.add(new SearchData("I.82_BM. Công nghệ phần mềm", R.drawable.ic_home, "82", "BM. Công nghệ phần mềm","8","I","ToaI"));
            countryList.add(new SearchData("I.81_BM. Khoa học máy tính", R.drawable.ic_home, "81", "BM. Khoa học máy tính","8","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 8 tòa I)", R.drawable.ic_home, "", "WC8","8","I","ToaI"));

            /**Tầng 9*/
            countryList.add(new SearchData("I.91", R.drawable.ic_home, "91", "","9","I","ToaI"));
            countryList.add(new SearchData("I.92", R.drawable.ic_home, "92", "","9","I","ToaI"));
            countryList.add(new SearchData("Thư viện (tầng 9)", R.drawable.ic_home, "", "Thư viện","9","I","ToaI"));

            /**Tầng 10*/
            countryList.add(new SearchData("Phòng luận văn", R.drawable.ic_home, "", "Phòng luận văn","10","I","ToaI"));
            countryList.add(new SearchData("Thư viện (tầng 10)", R.drawable.ic_home, "", "Thư viện","10","I","ToaI"));

            /**Tầng 11*/
            countryList.add(new SearchData("I.11E", R.drawable.ic_home, "11E", "","11","I","ToaI"));
            countryList.add(new SearchData("I.11F", R.drawable.ic_home, "11F", "","11","I","ToaI"));
            countryList.add(new SearchData("I.11G2", R.drawable.ic_home, "11G2", "","11","I","ToaI"));
            countryList.add(new SearchData("I.11G1", R.drawable.ic_home, "11G1", "","11","I","ToaI"));
            countryList.add(new SearchData("ITEC office", R.drawable.ic_home, "", "ITEC office","11","I","ToaI"));
            countryList.add(new SearchData("Phòng thông tin truyền thông (tầng 11)", R.drawable.ic_home, "", "Phòng thông tin truyền thông","11","I","ToaI"));
            countryList.add(new SearchData("I.11C_Phòng học", R.drawable.ic_home, "11C", "Phòng học","11","I","ToaI"));
            countryList.add(new SearchData("I.11B_Phòng học", R.drawable.ic_home, "11B", "Phòng học","11","I","ToaI"));
            countryList.add(new SearchData("I.11A_Phòng học", R.drawable.ic_home, "11A", "Phòng học","11","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 11 tòa I)", R.drawable.ic_home, "", "WC11","11","I","ToaI"));

            /**Tầng 12*/
            countryList.add(new SearchData("I.12C", R.drawable.ic_home, "12C", "","12","I","ToaI"));
            countryList.add(new SearchData("I.12B_Khoa ĐTVT\n" + "PTN CLC 2", R.drawable.ic_home, "12B", "Khoa ĐTVT\n" + "PTN CLC 2","12","I","ToaI"));
            countryList.add(new SearchData("I.12A", R.drawable.ic_home, "12A", "","12","I","ToaI"));

        }

        /**GD1*/
        {
            countryList.add(new SearchData("Giảng Đường 1", R.drawable.ic_home, "", "Giảng Đường 1", "Trệt", "GD1"));
        }

        /**GD2*/
        {
            countryList.add(new SearchData("Giảng Đường 2", R.drawable.ic_home, "", "Giảng Đường 2", "Trệt", "GD2"));
        }

        /**TTTH*/
        {
            countryList.add(new SearchData("Trung Tâm Tin Học", R.drawable.ic_home, "", "Trung Tâm Tin Học", "Trệt", "TTTH"));

        }
    }
    private void fillCountryList2() {
        /**A*/
        {
            /**Tầng Trệt*/
            countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("A.01_Văn phòng khoa vật lý - vật lý kỹ thuật", R.drawable.ic_home, "A01", "Văn phòng khoa vật lý - vật lý kỹ thuật", "TangTret", "A","ToaA"));
            countryList2.add(new SearchData("A.02_Phòng công tác sinh viên", R.drawable.ic_home, "A02", "Phòng công tác sinh viên", "TangTret", "A","ToaA"));
            countryList2.add(new SearchData("A.03_Phòng phó hiệu trưởng", R.drawable.ic_home, "A03", "Phòng phó hiệu trưởng", "TangTret", "A","ToaA"));
            countryList2.add(new SearchData("A.04_Phòng quản trị thiết bị", R.drawable.ic_home, "A04", "Phòng quản trị thiết bị", "TangTret", "A","ToaA"));
            countryList2.add(new SearchData("BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", R.drawable.ic_home, "A05", "BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", "TangTret", "A","ToaA"));

            /**Tầng 1*/
            countryList2.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A11", "PTN. BM. Hóa hữu cơ","Tang1","A","ToaA"));
            countryList2.add(new SearchData("BM. Hóa hữu cơ", R.drawable.ic_home, "A12", "BM. Hóa hữu cơ","Tang1","A","ToaA"));
            countryList2.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A13", "PTN. BM. Hóa hữu cơ","Tang1","A","ToaA"));

            /**Tầng 2*/
            countryList2.add(new SearchData("PTN. Hợp chất và hóa dược", R.drawable.ic_home, "A21", "PTN. Hợp chất và hóa dược","Tang2","A","ToaA"));
            countryList2.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A22", "PTN. BM. Hóa hữu cơ","Tang2","A","ToaA"));

        }

        /**B*/
        {
            /**Tầng Trệt*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("B.01_Phòng kế hoạch - tài chính", R.drawable.ic_home, "B01", "Phòng kế hoạch - tài chính", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.02_Phòng đào tạo", R.drawable.ic_home, "B02", "Phòng đào tạo", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.03", R.drawable.ic_home, "03", "B03", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.04_Phòng khảo thí và đảm bảo chất lượng", R.drawable.ic_home, "B04", "Phòng khảo thí và đảm bảo chất lượng", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.05_Văn phòng hiệu trưởng", R.drawable.ic_home, "B05", "Văn phòng hiệu trưởng", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.06_Phòng tổ chức - hành chính", R.drawable.ic_home, "B06", "Phòng tổ chức - hành chính", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.07_Phòng khoa học và công nghệ", R.drawable.ic_home, "B07", "Phòng khoa học và công nghệ", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.08_Phòng đào tạo sau đại học", R.drawable.ic_home, "B08", "Phòng đào tạo sau đại học", "TangTret", "B","ToaB"));
            countryList2.add(new SearchData("B.09", R.drawable.ic_home, "B09", "", "TangTret", "B","ToaB"));

            /**Tầng 1*/
            countryList2.add(new SearchData("B.11A", R.drawable.ic_home, "B11A", "", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.11B", R.drawable.ic_home, "B11B", "", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.13_BM. CNSH thực vật & chuyển hóa sinh học", R.drawable.ic_home, "B13", "BM. CNSH thực vật & chuyển hóa sinh học", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.14", R.drawable.ic_home, "B14", "", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.15", R.drawable.ic_home, "B15", "", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.16_PTN. Phân tích trung tâm", R.drawable.ic_home, "B16", "PTN. Phân tích trung tâm", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.17", R.drawable.ic_home, "B17", "", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.18_PTN. CNSH phân tử A", R.drawable.ic_home, "B18", "PTN. CNSH phân tử A", "Tang1", "B","ToaB"));
            countryList2.add(new SearchData("B.19_PTN. CNSH phân tử A", R.drawable.ic_home, "B19", "PTN. CNSH phân tử A", "Tang1", "B","ToaB"));

            /**Tầng 2*/
            countryList2.add(new SearchData("WC cbnv nữ (tầng 2 tòa B)", R.drawable.ic_home, "WC", "WC cbnv nữ", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.21_BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", R.drawable.ic_home, "B21", "BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.22_BM. Di truyền học\n" + "PTN. Di truyền", R.drawable.ic_home, "B22", "BM. Di truyền học\n" + "PTN. Di truyền", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.23_BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", R.drawable.ic_home, "B23", "BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.24_BM. Hóa lý\n" + "PTN. Hóa xúc tác", R.drawable.ic_home, "B24", "BM. Hóa lý\n" + "PTN. Hóa xúc tác", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.25", R.drawable.ic_home, "B25", "", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.26_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "B26", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.27_BM. Hóa phân tích", R.drawable.ic_home, "B27", "BM. Hóa phân tích", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.28_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "B28", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "Tang2", "B","ToaB"));
            countryList2.add(new SearchData("B.29", R.drawable.ic_home, "B29", "", "Tang2", "B","ToaB"));

            /**Tầng 3*/
            countryList2.add(new SearchData("WC cbnv nam (tầng 3 tòa B)", R.drawable.ic_home, "WC", "WC cbnv nam", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.30_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", R.drawable.ic_home, "B30", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.31_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", R.drawable.ic_home, "B31", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.32_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", R.drawable.ic_home, "B32", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.33_BM. Hải dương - khí tượng - thủy văn", R.drawable.ic_home, "B33", "BM. Hải dương - khí tượng - thủy văn", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.34_BM. Vật lý địa cầu", R.drawable.ic_home, "B34", "BM. Vật lý địa cầu", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.35_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", R.drawable.ic_home, "B35", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.36_Khoa vật lý\n" + "Phòng máy tính", R.drawable.ic_home, "B36", "Khoa vật lý\n" + "Phòng máy tính", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.37", R.drawable.ic_home, "B37", "", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.38_BM. Vật lý lý thuyết", R.drawable.ic_home, "B38", "BM. Vật lý lý thuyết", "Tang3", "B","ToaB"));
            countryList2.add(new SearchData("B.39_BM. Vật lý địa cầu", R.drawable.ic_home, "B39", "BM. Vật lý địa cầu", "Tang3", "B","ToaB"));

            /**Tầng 4*/
            countryList2.add(new SearchData("B.40a_Phòng máy tính 1", R.drawable.ic_home, "B40A", "Phòng máy tính 1", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.40", R.drawable.ic_home, "B40", "", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.41_Phòng học", R.drawable.ic_home, "B41", "Phòng học", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.42_Phòng học", R.drawable.ic_home, "B42", "Phòng học", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.43_Phòng học", R.drawable.ic_home, "B43", "Phòng học", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.44", R.drawable.ic_home, "B44", "", "Tang4", "B","ToaB"));
            countryList2.add(new SearchData("B.45_Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", R.drawable.ic_home, "B45", "Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", "Tang4", "B","ToaB"));

        }

        /**C*/
        {
            /**Tầng Trệt*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("C05_Phòng chuyên đề", R.drawable.ic_home, "C05", "Phòng chuyên đề", "TangTret", "C","ToaC"));
            countryList2.add(new SearchData("C04_PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển" + "PTN. Trầm tích học", R.drawable.ic_home, "C04", "PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển", "TangTret", "C","ToaC"));
            countryList2.add(new SearchData("C03_BM. Địa chất thủy văn - địa chất công trình", R.drawable.ic_home, "C03", "BM. Địa chất thủy văn - địa chất công trình", "TangTret", "C","ToaC"));
            countryList2.add(new SearchData("C02_BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", R.drawable.ic_home, "C02", "BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", "TangTret", "C","ToaC"));
            countryList2.add(new SearchData("C01_BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", R.drawable.ic_home, "C01", "BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", "TangTret", "C","ToaC"));

            /**Tầng 1*/
            countryList2.add(new SearchData("C15_Văn phòng khoa môi trường", R.drawable.ic_home, "C15", "Văn phòng khoa môi trường","Tang1","C","ToaC"));
            countryList2.add(new SearchData("Khoa môi trường\n" + "Phòng chuyên đề", R.drawable.ic_home, "14AC", "Khoa môi trường\n" + "Phòng chuyên đề","Tang1","C","ToaC"));
            countryList2.add(new SearchData("C14_PTN. Phân tích và kiểm soát ô nhiễm môi trường", R.drawable.ic_home, "C14", "PTN. Phân tích và kiểm soát ô nhiễm môi trường","Tang1","C","ToaC"));
            countryList2.add(new SearchData("WC cbnv nữ (tầng 1 tòa C)", R.drawable.ic_home, "WC", "WC cbnv nữ","Tang1","C","ToaC"));
            countryList2.add(new SearchData("C12A_Văn phòng khoa địa chất", R.drawable.ic_home, "C12A", "Văn phòng khoa địa chất","Tang1","C","ToaC"));
            countryList2.add(new SearchData("Khoa địa chất", R.drawable.ic_home, "C12B", "Khoa địa chất","Tang1","C","ToaC"));
            countryList2.add(new SearchData("C12_BM. Địa chất dầu khí", R.drawable.ic_home, "C12", "BM. Địa chất dầu khí","Tang1","C","ToaC"));
            countryList2.add(new SearchData("C11_PTN. Địa hóa và địa chất môi trường", R.drawable.ic_home, "C11", "PTN. Địa hóa và địa chất môi trường","Tang1","C","ToaC"));


            /**Tầng 2*/
            countryList2.add(new SearchData("C25_BM. Quản lý và tin học môi trường", R.drawable.ic_home, "C25", "BM. Quản lý và tin học môi trường","Tang2","C","ToaC"));
            countryList2.add(new SearchData("C24", R.drawable.ic_home, "C24", "","Tang2","C","ToaC"));
            countryList2.add(new SearchData("C23A_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "C23A", "Khoa CNTT \n" + "Phòng máy tính","Tang2","C","ToaC"));
            countryList2.add(new SearchData("C23B_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "C23B", "Khoa CNTT\n" + "Phòng máy tính","Tang2","C","ToaC"));
            countryList2.add(new SearchData("C22", R.drawable.ic_home, "C22", "","Tang2","C","ToaC"));
            countryList2.add(new SearchData("C21_PTN. Tinh thể - ngọc học", R.drawable.ic_home, "C21", "PTN. Tinh thể - ngọc học","Tang2","C","ToaC"));

            /**Tầng 3*/
            countryList2.add(new SearchData("C34_BM. Khoa học môi trường", R.drawable.ic_home, "C34", "BM. Khoa học môi trường","Tang3","C","ToaC"));
            countryList2.add(new SearchData("C34A_BM. Công nghệ môi trường", R.drawable.ic_home, "C34A", "BM. Công nghệ môi trường","Tang3","C","ToaC"));
            countryList2.add(new SearchData("C33", R.drawable.ic_home, "C33", "","Tang3","C","ToaC"));
            countryList2.add(new SearchData("WC nữ (tầng 3 tòa C)", R.drawable.ic_home, "WC", "WC nữ","Tang3","C","ToaC"));
            countryList2.add(new SearchData("C32B", R.drawable.ic_home, "C32B", "","Tang3","C","ToaC"));
            countryList2.add(new SearchData("C32A", R.drawable.ic_home, "C32A", "","Tang3","C","ToaC"));
            countryList2.add(new SearchData("C31", R.drawable.ic_home, "C31", "","Tang3","C","ToaC"));

            /**Tầng 4*/
            countryList2.add(new SearchData("C44_Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán", R.drawable.ic_home, "C44", "Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán","Tang4","C","ToaC"));

            countryList2.add(new SearchData("C43B", R.drawable.ic_home, "C43B", "","Tang4","C","ToaC"));
            countryList2.add(new SearchData("C43A", R.drawable.ic_home, "C43A", "","Tang4","C","ToaC"));
            countryList2.add(new SearchData("C42", R.drawable.ic_home, "C42", "","Tang4","C","ToaC"));
            countryList2.add(new SearchData("C41", R.drawable.ic_home, "C41", "","Tang4","C","ToaC"));


        }

        /**D*/
        {
            /**Tầng Trệt*/
            countryList2.add(new SearchData("Trung tâm khoa học và công nghệ sinh học", R.drawable.ic_home, "D01", "Trung tâm khoa học và công nghệ sinh học","TangTret", "D","ToaD"));

            /**Tầng 1*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("PTN. Thực vật_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "D11", "Phòng học\n" + "Phòng thí nghiệm","Tang1","D","ToaD"));
            countryList2.add(new SearchData("PTN. Thực vật_Phòng nghiên cứu", R.drawable.ic_home, "D12", "Phòng nghiên cứu","Tang1","D","ToaD"));

            /**Tầng 2*/
            countryList2.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "D21", "Phòng học\n" + "Phòng thí nghiệm","Tang2","D","ToaD"));
            countryList2.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng nghiên cứu", R.drawable.ic_home, "D22", "Phòng nghiên cứu","Tang2","D","ToaD"));

        }

        /**E*/
        {
            /**Tầng Trệt*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("Phòng khoa học và công nghệ", R.drawable.ic_home, "E01", "Phòng khoa học và công nghệ", "TangTret", "E","ToaE"));
            countryList2.add(new SearchData("Phòng y tế", R.drawable.ic_home, "E02", "Phòng y tế", "TangTret", "E","ToaE"));
            countryList2.add(new SearchData("BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", R.drawable.ic_home, "E03", "BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", "TangTret", "E","ToaE"));
            countryList2.add(new SearchData("PTN. Vi sinh - Sinh môi", R.drawable.ic_home, "E04", "PTN. Vi sinh - Sinh môi", "TangTret", "E","ToaE"));

            /**Tầng 1*/
            countryList2.add(new SearchData("E101C_Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học", R.drawable.ic_home, "E101C", "Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E101A_Khoa ĐTVT\n" + "Phòng họp", R.drawable.ic_home, "E101A", "Khoa ĐTVT\n" + "Phòng họp","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E101B_PTN. DESLAB", R.drawable.ic_home, "E101B", "PTN. DESLAB","Tang1","E","ToaE"));
            countryList2.add(new SearchData("WC nữ (tầng 1 tòa E)\n" + "VC - NLĐ", R.drawable.ic_home, "WC", "WC nữ\n" + "VC - NLĐ","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E102_Khoa điện tử - viễn thông\n" + "Phòng máy tính", R.drawable.ic_home, "E102", "Khoa điện tử - viễn thông\n" + "Phòng máy tính","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E103A_BM. Máy tính - hệ thống nhúng", R.drawable.ic_home, "E103A", "BM. Máy tính - hệ thống nhúng","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E103B_PTN. Máy tính - hệ thống nhúng", R.drawable.ic_home, "E103B", "PTN. Máy tính - hệ thống nhúng","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E104_PTN. Viễn thông", R.drawable.ic_home, "E104", "PTN. Viễn thông","Tang1","E","ToaE"));
            countryList2.add(new SearchData("PTN. Điện tử", R.drawable.ic_home, "E105A", "PTN. Điện tử","Tang1","E","ToaE"));
            countryList2.add(new SearchData("BM. Điện tử", R.drawable.ic_home, "E105B", "BM. Điện tử","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E106_BM. Viễn thông và mạng", R.drawable.ic_home, "E106", "BM. Viễn thông và mạng","Tang1","E","ToaE"));
            countryList2.add(new SearchData("E107_Văn phòng khoa điện tử - viễn thông", R.drawable.ic_home, "E107", "Văn phòng khoa điện tử - viễn thông" ,"Tang1","E","ToaE"));

            /**Tầng 2*/
            countryList2.add(new SearchData("E201B", R.drawable.ic_home, "E201B", "","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E201A_BM. Giải tích", R.drawable.ic_home, "E201A", "BM. Giải tích","Tang2","E","ToaE"));
            countryList2.add(new SearchData("WC nam (tầng 2 tòa E)", R.drawable.ic_home, "WC", "WC nam","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E202B_Phòng máy vi tính", R.drawable.ic_home, "E202B", "Phòng máy vi tính","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E202A_BM. Ứng dụng tin học", R.drawable.ic_home, "E202A", "BM. Ứng dụng tin học","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E203_BM. Vật lý chất rắn", R.drawable.ic_home, "E203", "BM. Vật lý chất rắn","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E204", R.drawable.ic_home, "E204", "","Tang2","E","ToaE"));
            countryList2.add(new SearchData("E205_BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng", R.drawable.ic_home, "E205", "BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng","Tang2","E","ToaE"));
            countryList2.add(new SearchData("L2.P6", R.drawable.ic_home, "L2.P6", "","Tang2","E","ToaE"));

            /**Tầng 3*/
            countryList2.add(new SearchData("E301", R.drawable.ic_home, "E301", "","Tang3","E","ToaE"));
            countryList2.add(new SearchData("WC nữ (tầng 3 tòa E)", R.drawable.ic_home, "WC", "WC nữ","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E302", R.drawable.ic_home, "E302", "","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E303A_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử", R.drawable.ic_home, "E303A", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E303B_Phòng thực tập điện tử", R.drawable.ic_home, "E303B", "Phòng thực tập điện tử","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E304_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học", R.drawable.ic_home, "E304", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học","Tang3","E","ToaE"));
            countryList2.add(new SearchData("BM. Vật lý tin học\n" + "Phòng máy tính", R.drawable.ic_home, "E304A", "BM. Vật lý tin học\n" + "Phòng máy tính","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E305_PTN. Thiết kế vi mạch và hệ thống nhúng", R.drawable.ic_home, "E305", "PTN. Thiết kế vi mạch và hệ thống nhúng","Tang3","E","ToaE"));
            countryList2.add(new SearchData("E306_BM. Vật lý tin học\n" + "PTN chuyên đề", R.drawable.ic_home, "E306", "BM. Vật lý tin học\n" + "PTN chuyên đề","Tang3","E","ToaE"));

            /**Tầng 4*/
            countryList2.add(new SearchData("E401", R.drawable.ic_home, "E401", "","Tang4","E","ToaE"));
            countryList2.add(new SearchData("WC nam (tầng 4 tòa E)", R.drawable.ic_home, "WC", "WC nam","Tang4","E","ToaE"));
            countryList2.add(new SearchData("E402", R.drawable.ic_home, "E402", "","Tang4","E","ToaE"));
            countryList2.add(new SearchData("E403", R.drawable.ic_home, "E403", "","Tang4","E","ToaE"));
            countryList2.add(new SearchData("E404", R.drawable.ic_home, "E404", "","Tang4","E","ToaE"));
            countryList2.add(new SearchData("E405", R.drawable.ic_home, "E405", "","Tang4","E","ToaE"));
            countryList2.add(new SearchData("E406", R.drawable.ic_home, "E406", "","Tang4","E","ToaE"));


        }

        /**F*/
        {
            /**Tầng Trệt*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("WC nữ (tầng trệt tòa F)", R.drawable.ic_home, "WC1", "WC nữ","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.01_Phòng tổ chức hành chính", R.drawable.ic_home, "F01", "Phòng tổ chức hành chính","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.02_Phòng thông tin - truyền thông", R.drawable.ic_home, "F02", "Phòng thông tin - truyền thông","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.03_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "F03", "BM. Di truyền\n" + "PTN. Sinh học phân tử","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.04_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "F04", "BM. Di truyền\n" + "PTN. Sinh học phân tử","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.06_Văn phòng khoa sinh học - công nghệ sinh học", R.drawable.ic_home, "F06", "Văn phòng khoa sinh học - công nghệ sinh học","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.07_Phòng kế hoạch - tài chính", R.drawable.ic_home, "F07", "Phòng kế hoạch - tài chính","TangTret","F","ToaF"));
            countryList2.add(new SearchData("WC cbvc nữ (tầng trệt tòa F)", R.drawable.ic_home, "WC2", "WC cbvc nữ","TangTret","F","ToaF"));
            countryList2.add(new SearchData("Tổ giảng đường", R.drawable.ic_home, "F07A", "Tổ giảng đường","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.08_Văn phòng khoa toán - tin học", R.drawable.ic_home, "F08", "Văn phòng khoa toán - tin học","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.09_Khoa toán - tin học", R.drawable.ic_home, "F09", "Khoa toán - tin học","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.10_Trung tâm khoa học - toán học", R.drawable.ic_home, "F10", "Trung tâm khoa học - toán học","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.11_BM. Cơ học", R.drawable.ic_home, "F11", "BM. Cơ học","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.12_BM. Xác suất thống kê", R.drawable.ic_home, "F12", "BM. Xác suất thống kê","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.13_BM. Tối ưu & hệ thống", R.drawable.ic_home, "F13", "BM. Tối ưu & hệ thống","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.14_BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không", R.drawable.ic_home, "F14", "BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.15_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "F15", "PTN. Vật liệu kỹ thuật cao","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.16_PTN. Tổng hợp & phân tích vật liệu màng mỏng", R.drawable.ic_home, "F16", "PTN. Tổng hợp & phân tích vật liệu màng mỏng","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.17_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "F17", "PTN. Vật liệu kỹ thuật cao","TangTret","F","ToaF"));
            countryList2.add(new SearchData("WC nam (tầng trệt tòa F)", R.drawable.ic_home, "WC3", "WC nam","TangTret","F","ToaF"));
            countryList2.add(new SearchData("F.18_PTN. Tổng hợp vật liệu màng mỏng", R.drawable.ic_home, "F18", "PTN. Tổng hợp vật liệu màng mỏng","TangTret","F","ToaF"));

            /**Tầng 1*/

            countryList2.add(new SearchData("WC nam (tầng 1 tòa F)", R.drawable.ic_home, "WC", "WC nam","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.100_BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường", R.drawable.ic_home, "F100", "BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.101", R.drawable.ic_home, "F101", "","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.101A_Phòng quan hệ đối ngoại", R.drawable.ic_home, "F101A", "Phòng quan hệ đối ngoại","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.101B_PTN. CNSH phân tử (B)", R.drawable.ic_home, "F101B", "PTN. CNSH phân tử (B)","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.102_Phòng họp", R.drawable.ic_home, "F102", "Phòng họp","Tang1","F","ToaF"));
            countryList2.add(new SearchData("Rest room", R.drawable.ic_home, "F102A", "rest room","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.103_Văn phòng hội đồng trường", R.drawable.ic_home, "F103", "Văn phòng hội đồng trường","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.104", R.drawable.ic_home, "F104", "","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.105_Phòng quan hệ đối ngoại", R.drawable.ic_home, "F105", "Phòng quan hệ đối ngoại","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.106A_Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh", R.drawable.ic_home, "F106A", "Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.106B_Văn phòng công đoàn", R.drawable.ic_home, "F106B", "Văn phòng công đoàn","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.107_Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế", R.drawable.ic_home, "F107", "Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.108_Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên", R.drawable.ic_home, "F108", "Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.109_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "F109", "Phòng máy\n" + "Khoa CNTT","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.110_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "F110", "Phòng máy\n" + "Khoa CNTT","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.111", R.drawable.ic_home, "F111", "","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.112A_Khoa khoa học và công nghệ vật liệu", R.drawable.ic_home, "F112A", "Khoa khoa học và công nghệ vật liệu","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.112_BM. Sinh lý học và CNSH động vật", R.drawable.ic_home, "F112", "BM. Sinh lý học và CNSH động vật","Tang1","F","ToaF"));
            countryList2.add(new SearchData("F.113_Văn phòng khoa Khoa khoa học và công nghệ vật  liệu", R.drawable.ic_home, "F113", "Văn phòng khoa Khoa khoa học và công nghệ vật  liệu","Tang1","F","ToaF"));

            /**Tầng 2*/
            countryList2.add(new SearchData("Khoa môi trường - phòng đọc", R.drawable.ic_home, "F200A", "Khoa môi trường - phòng đọc","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.200_Khoa môi trường\n" + "PTN. Tin học môi trường", R.drawable.ic_home, "F200", "Khoa môi trường\n" + "PTN. Tin học môi trường","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.202", R.drawable.ic_home, "F202", "","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.203", R.drawable.ic_home, "F203", "","Tang2","F","ToaF"));
            countryList2.add(new SearchData("University council - office of the chair", R.drawable.ic_home, "F204", "University council - office of the chair","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F205A", R.drawable.ic_home, "F205A", "","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F205B", R.drawable.ic_home, "F205B", "","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.206A_BM. Tài chính định lượng", R.drawable.ic_home, "F206A", "BM. Tài chính định lượng","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.206_BM. Giáo dục toán học", R.drawable.ic_home, "F206", "BM. Giáo dục toán học","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.207", R.drawable.ic_home, "F207", "","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.208_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "F208", "Phòng máy\n" + "Khoa toán - tin học","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.209_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "F209", "Phòng máy\n" + "Khoa toán - tin học","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.210_BM. Đại số", R.drawable.ic_home, "F210", "BM. Đại số","Tang2","F","ToaF"));
            countryList2.add(new SearchData("F.211_PTN. Tổng hợp vật liệu polyme", R.drawable.ic_home, "F211", "PTN. Tổng hợp vật liệu polyme","Tang2","F","ToaF"));

            /**Tầng 3*/
            countryList2.add(new SearchData("F.300", R.drawable.ic_home, "F300", "","Tang3","F","ToaF"));
            countryList2.add(new SearchData("F.301_Phòng học", R.drawable.ic_home, "F301", "Phòng học","Tang3","F","ToaF"));
            countryList2.add(new SearchData("F.302", R.drawable.ic_home, "F302", "","Tang3","F","ToaF"));
            countryList2.add(new SearchData("F.303_Phòng học", R.drawable.ic_home, "F303", "Phòng học","Tang3","F","ToaF"));
            countryList2.add(new SearchData("F.304_Phòng học", R.drawable.ic_home, "F304", "Phòng học","Tang3","F","ToaF"));
//                countryList2.add(new SearchData("F.305_Phòng học", R.drawable.ic_home, "305", "Phòng học","Tang3","F","ToaF"));
            countryList2.add(new SearchData("BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh", R.drawable.ic_home, "F305a", "BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh","Tang3","F","ToaF"));
            countryList2.add(new SearchData("F.305_PTN. Phân tích vật liệu", R.drawable.ic_home, "F305", "PTN. Phân tích vật liệu","Tang3","F","ToaF"));

            /**Tầng 4*/
            countryList2.add(new SearchData("F.400_PTN. Phân tích vật liệu polyme", R.drawable.ic_home, "F400", "PTN. Phân tích vật liệu polyme","Tang4","F","ToaF"));


        }

        /**H*/
        {
            /**Tầng Trệt*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("H2.2", R.drawable.ic_home, "H22", "", "TangTret", "H","ToaH"));
            countryList2.add(new SearchData("H2.1", R.drawable.ic_home, "H21", "", "TangTret", "H","ToaH"));

            /**Tầng 1*/
            countryList2.add(new SearchData("H2.3", R.drawable.ic_home, "H23", "","Tang1","H","ToaH"));

        }

        /**I*/
        {

            /**Tầng 1*/
            //countryList2 = new ArrayList<>();
            countryList2.add(new SearchData("Hội trường I", R.drawable.ic_home, "HoiTruongI", "Hội trường I", "Tang1", "I","ToaI"));
            countryList2.add(new SearchData("I.12_Phòng họp", R.drawable.ic_home, "I12", "Phòng họp", "Tang1", "I","ToaI"));
            countryList2.add(new SearchData("I.11_Phòng họp", R.drawable.ic_home, "I11", "Phòng họp", "Tang1", "I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 1 tòa I)", R.drawable.ic_home, "WC", "WC1","Tang1","I","ToaI"));


            /**Tầng 2*/
            countryList2.add(new SearchData("I.25_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "I25", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","Tang2","I","ToaI"));
            countryList2.add(new SearchData("I.26", R.drawable.ic_home, "I26", "","Tang2","I","ToaI"));
            countryList2.add(new SearchData("I.27_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "I27", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","Tang2","I","ToaI"));
            countryList2.add(new SearchData("I.24", R.drawable.ic_home, "I24", "","Tang2","I","ToaI"));
            countryList2.add(new SearchData("I.23", R.drawable.ic_home, "I23", "","Tang2","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 2 tòa I)", R.drawable.ic_home, "WC", "WC2","Tang2","I","ToaI"));

            /**Tầng 3*/
            countryList2.add(new SearchData("I.35_PTN. Hóa vô cơ chuyên ngành", R.drawable.ic_home, "I35", "PTN. Hóa vô cơ chuyên ngành","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.36_BM. Hóa vô cơ và ứng dụng", R.drawable.ic_home, "I36", "BM. Hóa vô cơ và ứng dụng","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.37_Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng", R.drawable.ic_home, "I37", "Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.38_Phòng hội thảo", R.drawable.ic_home, "I38", "Phòng hội thảo","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.31", R.drawable.ic_home, "I31", "","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.32", R.drawable.ic_home, "I32", "","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.33", R.drawable.ic_home, "I33", "","Tang3","I","ToaI"));
            countryList2.add(new SearchData("I.34", R.drawable.ic_home, "I34", "","Tang3","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 3 tòa I)", R.drawable.ic_home, "WC", "WC3","Tang3","I","ToaI"));

            /**Tầng 4*/
            countryList2.add(new SearchData("I.46_PTN. Hóa dược", R.drawable.ic_home, "I46", "PTN. Hóa dược","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.47_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "I47", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.48_BM. Hóa phân tích", R.drawable.ic_home, "I48", "BM. Hóa phân tích","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.44_APCS-404", R.drawable.ic_home, "I44", "APCS-404","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.43_Văn phòng các chương trình đào tạo đặc biệt ngành CNTT", R.drawable.ic_home, "I43", "Văn phòng các chương trình đào tạo đặc biệt ngành CNTT","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.42_APCS-402", R.drawable.ic_home, "I42", "APCS-402","Tang4","I","ToaI"));
            countryList2.add(new SearchData("I.41_APCS-401", R.drawable.ic_home, "I41", "APCS-401","Tang4","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 4 tòa I)", R.drawable.ic_home, "WC", "WC4","Tang4","I","ToaI"));


            /**Tầng 5*/
            countryList2.add(new SearchData("I.56_PTN. Hóa lý ứng dụng", R.drawable.ic_home, "I56", "PTN. Hóa lý ứng dụng","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.57_BM. Hóa lý\n" + "PTN. Hóa nano", R.drawable.ic_home, "I57", "BM. Hóa lý\n" + "PTN. Hóa nano","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.58_BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ", R.drawable.ic_home, "I58", "BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.59_BM. Hóa dược", R.drawable.ic_home, "I59", "BM. Hóa dược","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.59A_Phòng hội thảo", R.drawable.ic_home, "I59A", "Phòng hội thảo","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.59B_Văn phòng khoa", R.drawable.ic_home, "I59B", "Văn phòng khoa","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.55_Phòng họp", R.drawable.ic_home, "I55", "Phòng họp","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.54_Văn phòng khoa CNTT", R.drawable.ic_home, "I54", "Văn phòng khoa CNTT","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.53_Bộ phận giáo vụ\n" + "Trợ lý sinh viên", R.drawable.ic_home, "I53", "Bộ phận giáo vụ\n" + "Trợ lý sinh viên","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.52_Phòng máy tính", R.drawable.ic_home, "I52", "Phòng máy tính","Tang5","I","ToaI"));
            countryList2.add(new SearchData("I.51_Bộ phận kỹ thuật", R.drawable.ic_home, "I51", "Bộ phận kỹ thuật","Tang5","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 5 tòa I)", R.drawable.ic_home, "WC", "WC5","Tang5","I","ToaI"));

            /**Tầng 6*/
            countryList2.add(new SearchData("I.65_BM. Hóa học cao phân tử\n" + "Polyme 1", R.drawable.ic_home, "I65", "BM. Hóa học cao phân tử\n" + "Polyme 1", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.66_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "I66", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.67_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "I67", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.68_BM. Hóa lý\n" + "PTN. Điện hóa", R.drawable.ic_home, "I68", "BM. Hóa lý\n" + "PTN. Điện hóa", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.69_BM. Hóa học cao phân tử\n" + "PTN. Polyme 2" , R.drawable.ic_home, "I69", "BM. Hóa học cao phân tử\n" + "PTN. Polyme 2", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.69A_Văn phòng bộ môn", R.drawable.ic_home, "I69A", "Văn phòng bộ môn", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.64_PTN. Trí tuệ nhân tạo", R.drawable.ic_home, "I64", "PTN. Trí tuệ nhân tạo", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.63_BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", R.drawable.ic_home, "I63", "BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.62_Phòng máy tính", R.drawable.ic_home, "I62", "Phòng máy tính", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("I.61_Phòng máy tính", R.drawable.ic_home, "I61", "Phòng máy tính", "Tang6", "I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 6 tòa I)", R.drawable.ic_home, "WC", "WC6","Tang6","I","ToaI"));

            /**Tầng 7*/
            countryList2.add(new SearchData("I.76_Phòng họp sinh viên", R.drawable.ic_home, "I76", "Phòng họp sinh viên","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.77_PTN. Hóa tin", R.drawable.ic_home, "I77", "PTN. Hóa tin","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.78_PTN. Hóa tin", R.drawable.ic_home, "I78", "PTN. Hóa tin","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.79", R.drawable.ic_home, "I79", "","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.79A", R.drawable.ic_home, "I79A", "","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.75", R.drawable.ic_home, "I75", "","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.74_BM. Mạng máy tính và viễn thông", R.drawable.ic_home, "I74", "BM. Mạng máy tính và viễn thông","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.73_PTN. An ninh mạng", R.drawable.ic_home, "I73", "PTN. An ninh mạng","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.72_BM. Thị giác máy tính và khoa học robot", R.drawable.ic_home, "I72", "BM. Thị giác máy tính và khoa học robot","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.72A_Phòng họp", R.drawable.ic_home, "I72A", "Phòng họp","Tang7","I","ToaI"));
            countryList2.add(new SearchData("I.71_Phòng máy tính", R.drawable.ic_home, "I71", "Phòng máy tính","Tang7","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 7 tòa I)", R.drawable.ic_home, "WC", "WC7","Tang7","I","ToaI"));

            /**Tầng 8*/
            countryList2.add(new SearchData("I.86_PTN. Hệ thống nhúng", R.drawable.ic_home, "I86", "PTN. Hệ thống nhúng","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.87_PTN. Công nghệ phần mềm", R.drawable.ic_home, "I87", "PTN. Công nghệ phần mềm","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.87A_PTN. Hệ thống di động & media", R.drawable.ic_home, "I87A", "PTN. Hệ thống di động & media","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.88", R.drawable.ic_home, "I88", "","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.89_Phòng vật lý tính toán", R.drawable.ic_home, "I89", "Phòng vật lý tính toán","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.85_Phòng thông tin - truyền thông", R.drawable.ic_home, "I85", "Phòng thông tin - truyền thông","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.84_BM. Hệ thống thông tin", R.drawable.ic_home, "I84", "BM. Hệ thống thông tin","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.82_BM. Công nghệ phần mềm", R.drawable.ic_home, "I82", "BM. Công nghệ phần mềm","Tang8","I","ToaI"));
            countryList2.add(new SearchData("I.81_BM. Khoa học máy tính", R.drawable.ic_home, "I81", "BM. Khoa học máy tính","Tang8","I","ToaI"));
            countryList2.add(new SearchData("WC (tầng 8 tòa I)", R.drawable.ic_home, "WC", "WC8","Tang8","I","ToaI"));

            /**Tầng 9*/
            countryList2.add(new SearchData("I.91", R.drawable.ic_home, "I91", "","Tang9","I","ToaI"));
            countryList2.add(new SearchData("I.92", R.drawable.ic_home, "I92", "","Tang9","I","ToaI"));
            countryList2.add(new SearchData("Thư viện (tầng 9)", R.drawable.ic_home, "ThuVien", "Thư viện","Tang9","I","ToaI"));

            /**Tầng 10*/
            countryList2.add(new SearchData("Phòng luận văn", R.drawable.ic_home, "LuanVan", "Phòng luận văn","Tang10","I","ToaI"));
            countryList2.add(new SearchData("Thư viện (tầng 10)", R.drawable.ic_home, "ThuVien", "Thư viện","Tang10","I","ToaI"));

            /**Tầng 11*/
            countryList2.add(new SearchData("I.11E", R.drawable.ic_home, "I11E", "","Tang11","I","ToaI"));
            countryList2.add(new SearchData("I.11F", R.drawable.ic_home, "I11F", "","Tang11","I","ToaI"));
            countryList2.add(new SearchData("I.11G2", R.drawable.ic_home, "I11G2", "","Tang11","I","ToaI"));
            countryList2.add(new SearchData("I.11G1", R.drawable.ic_home, "I11G1", "","Tang11","I","ToaI"));
            countryList2.add(new SearchData("ITEC office", R.drawable.ic_home, "ITEC", "ITEC office","Tang11","I","ToaI"));
            countryList2.add(new SearchData("Phòng thông tin truyền thông (tầng 11)", R.drawable.ic_home, "I11D", "Phòng thông tin truyền thông","Tang11","I","ToaI"));
            countryList2.add(new SearchData("I.11C_Phòng học", R.drawable.ic_home, "I11C", "Phòng học","Tang11","I","ToaI"));
            countryList2.add(new SearchData("I.11B_Phòng học", R.drawable.ic_home, "I11B", "Phòng học","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11A_Phòng học", R.drawable.ic_home, "I11A", "Phòng học","Tang11","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 11 tòa I)", R.drawable.ic_home, "WC", "WC11","Tang11","I","ToaI"));

            /**Tầng 12*/
            countryList.add(new SearchData("I.12C", R.drawable.ic_home, "I12C", "","Tang12","I","ToaI"));
            countryList.add(new SearchData("I.12B_Khoa ĐTVT\n" + "PTN CLC 2", R.drawable.ic_home, "I12B", "Khoa ĐTVT\n" + "PTN CLC 2","Tang12","I","ToaI"));
            countryList2.add(new SearchData("I.12A", R.drawable.ic_home, "I12A", "","Tang12","I","ToaI"));

        }

        /**GD1*/
        {
            countryList2.add(new SearchData("Giảng Đường 1", R.drawable.ic_home, "", "Giảng Đường 1", "TangTret", "GD1"));
        }

        /**GD2*/
        {
            countryList2.add(new SearchData("Giảng Đường 2", R.drawable.ic_home, "", "Giảng Đường 2", "TangTret", "GD2"));
        }

        /**TTTH*/
        {
            countryList2.add(new SearchData("Trung Tâm Tin Học", R.drawable.ic_home, "", "Trung Tâm Tin Học", "TangTret", "TTTH"));

        }
    }

    private String chiduong(String toabd, String toadd, String tangbd, String tangdd, String tenphongbd, String tenphongdd, String maphongbd, String maphongdd ){
        String chiduongdi = null;
        switch ( toabd ) {

            case "A":
                /**1*/
                if (toadd == "A") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";

                }
                /**2*/
                if (toadd == "F" || toadd == "GD1" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "B" || toadd == "C" || toadd == "D" || toadd == "E" || toadd == "H") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if (toadd == "GD2") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;

                }
                break;


            case "B":
                /**1*/
                if (toadd == "B") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";
                }
                /**2*/
                if (toadd == "F" || toadd == "GD1" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "A" || toadd == "C" || toadd == "D" || toadd == "E" || toadd == "H") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if (toadd == "GD2") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;

                }
                break;

            case "C":
                /**1*/
                if (toadd == "C") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";
                }
                /**2*/
                if (toadd == "F" || toadd == "GD1" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "A" || toadd == "B" || toadd == "D" || toadd == "E" || toadd == "H") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if (toadd == "GD2") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;

                }
                break;
            case "D":
                /**1*/
                if (toadd == "D") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";
                }
                /**2*/
                if (toadd == "F" || toadd == "GD1" || toadd == "H" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "A" || toadd == "B" || toadd == "C" || toadd == "E" || toadd == "GD2") {
                    if (toadd == "GD2") {
                        chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa H sau đó đến tòa " + toadd;
                    } else {
                        chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                    }
                }
                break;
            case "E":
                /**1*/
                if (toadd == "E") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";
                }
                /**2*/
                if (toadd == "F" || toadd == "GD1" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "A" || toadd == "B" || toadd == "C" || toadd == "D" || toadd == "H") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if (toadd == "GD2") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;

                }
                break;
            case "F":
                /**1*/
                if (toadd == "F") {
                    chiduongdi = "Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này";
                }
                /**2*/
                if (toadd == "A" || toadd == "B" || toadd == "C" || toadd == "D" || toadd == "E" || toadd == "GD1" || toadd == "H" || toadd == "I" || toadd == "TTTH") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if (toadd == "GD2") {
                    chiduongdi = "Bạn hãy đi từ tòa " + toabd + " đến tòa H sau đó đến tòa " + toadd;
                }
                break;
            case  "H":
                /**1*/
                if(toadd =="H") {
                    chiduongdi="Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này" ;
                }
                /**2*/
                if(toadd =="D"||toadd=="F"||toadd=="GD2") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if( toadd =="A" ||toadd =="B" || toadd =="C" || toadd =="E" ||toadd =="GD1" || toadd =="I" ||toadd =="TTTH"){
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }

                break;
            case  "I":
                /**1*/
                if(toadd =="I") {
                    chiduongdi="Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này" ;
                }
                /**2*/
                if(toadd =="A" ||toadd =="B" || toadd =="C" || toadd =="D" || toadd =="E" || toadd=="F" ||toadd =="GD1" ||toadd =="TTTH") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if(  toadd =="H" ){
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa D sau đó đến tòa " + toadd;
                }
                /**4*/
                if(toadd =="GD2") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa D sau đó đến tòa H và sau cùng qua tòa " + toadd;
                }
                break;
            case  "GD1":
                /**1*/
                if(toadd =="GD1") {
                    chiduongdi="Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này" ;
                }
                /**2*/
                if(toadd =="A" ||toadd =="B" || toadd =="C" || toadd =="D" || toadd =="E" ||toadd =="F"||toadd =="I"||toadd =="TTTH") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if( toadd =="H"){
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if(toadd =="GD2") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;
                }
                break;
            case  "GD2":
                /**1*/
                if(toadd =="GD2") {
                    chiduongdi="Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này" ;
                }
                /**2*/
                if(toadd == "H") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if( toadd =="D" || toadd =="F"){
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa H sau đó đến tòa " + toadd;
                }
                /**4*/
                if(toadd =="A" ||toadd =="B" || toadd =="C" || toadd =="E" || toadd =="GD1" || toadd =="I" || toadd =="TTTH") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa H sau đó đến tòa F và sau cùng qua tòa " + toadd;
                }
                break;
            case  "TTTH":
                /**1*/
                if(toadd =="TTTH") {
                    chiduongdi="Phòng Bạn tìm kiếm đang nằm trong tòa " + toadd + " này" ;
                }
                /**2*/
                if(toadd =="A" ||toadd =="B" || toadd =="C" || toadd =="D" || toadd =="E" ||toadd =="F"|| toadd =="GD1" ||toadd =="I") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa " + toadd;
                }
                /**3*/
                if( toadd =="H"){
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa " + toadd;
                }
                /**4*/
                if(toadd =="GD2") {
                    chiduongdi="Bạn hãy đi từ tòa " + toabd + " đến tòa F sau đó đến tòa H và sau cùng qua tòa " + toadd;
                }
                break;
            default:

        }


        return chiduongdi;
    }
}


