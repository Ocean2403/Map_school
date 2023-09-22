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

public class SearchFragment extends Fragment {
    private List<SearchData> countryList;
    private TextView textView,textView1,textView2,textView3;
    private TextView text1, text2, text3, text4;
    private ImageView hinh;
    private AutoCompleteTextView o;
    private ImageButton imgbtn, clearButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, null);

        fillCountryList();
        AutoCompleteTextView editText = view.findViewById(R.id.actv);
        SearchAdapter adapter = new SearchAdapter(getContext(),countryList);
        editText.setAdapter(adapter);
        textView = (TextView) view.findViewById(R.id.maphong);
        textView1 = (TextView) view.findViewById(R.id.tenphong);
        textView2 = (TextView) view.findViewById(R.id.tang);
        textView3 = (TextView) view.findViewById(R.id.toa);
        hinh= (ImageView) view.findViewById(R.id.hinh);
        imgbtn = (ImageButton) view.findViewById(R.id.search_btn);
        clearButton = (ImageButton) view.findViewById(R.id.clear_btn);

        text1 = (TextView) view.findViewById(R.id.maphong0);
        text2 = (TextView) view.findViewById(R.id.tenphong0);
        text3 = (TextView) view.findViewById(R.id.tang0);
        text4 = (TextView) view.findViewById(R.id.toa0);

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

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e=editText.getText().toString().trim().toLowerCase(Locale.ROOT);
                String toa="",tang="",tenphong="",maphong="",toakodau="";
                for(SearchData item :countryList){
                    String f=item.getSearch().trim().toLowerCase(Locale.ROOT);
                    if(e.length()==f.length()) {
                        if (e.equalsIgnoreCase(f)==true ) {
                            textView.setText(item.getMaphong());
                            textView1.setText(item.getTenphong());
                            textView2.setText(item.getTang());
                            textView3.setText(item.getToa());
                            //hinh.setImageResource(item.getHinh());
//                            Picasso.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/map3d-cc8be.appspot.com/o/20230323_102858.jpg?alt=media&token=b0709131-cad1-4176-9b7d-f5e8ff39167c").into(hinh);
                            text1.setVisibility(View.VISIBLE);
                            text2.setVisibility(View.VISIBLE);
                            text3.setVisibility(View.VISIBLE);
                            text4.setVisibility(View.VISIBLE);

                            toa=item.getToa();
                            tang=item.getTang();
                            tenphong=item.getTenphong();
                            maphong=item.getMaphong();
                            toakodau=item.getToakodau();
                            DocumentReference docRef = db.collection("DanhSach").document(toakodau).collection(tang).document(maphong);
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            Log.d(TAG, "DocumentSnapshot data: " + document.get("HinhAnh"));
                                            String a= (String) document.get("HinhAnh");
                                            if(a.equalsIgnoreCase("")==false) {
                                                Picasso.with(getContext()).load((String) document.get("HinhAnh")).into(hinh);
                                            }else {
                                                hinh.setImageResource(item.getHinh());

                                            }
                                        } else {
                                            Log.d(TAG, "No such document");
                                        }
                                    } else {
                                        Log.d(TAG, "get failed with ", task.getException());
                                    }
                                }
                            });
                            break;
                        }
                    }else{
                        textView.setText("Phòng này không hề tồn tại!");
                        textView1.setText("");
                        textView2.setText("");
                        textView3.setText("");
                        hinh.setImageResource(R.drawable.ic_home);
                    }


                }
            }

        });
        return view;
    }



    private void fillCountryList() {
        /**A*/
        {
            /**Tầng Trệt*/
            countryList = new ArrayList<>();
            countryList.add(new SearchData("A.01_Văn phòng khoa vật lý - vật lý kỹ thuật", R.drawable.ic_home, "A01", "Văn phòng khoa vật lý - vật lý kỹ thuật", "TangTret", "A","ToaA"));
            countryList.add(new SearchData("A.02_Phòng công tác sinh viên", R.drawable.ic_home, "A02", "Phòng công tác sinh viên", "TangTret", "A","ToaA"));
            countryList.add(new SearchData("A.03_Phòng phó hiệu trưởng", R.drawable.ic_home, "A03", "Phòng phó hiệu trưởng", "TangTret", "A","ToaA"));
            countryList.add(new SearchData("A.04_Phòng quản trị thiết bị", R.drawable.ic_home, "A04", "Phòng quản trị thiết bị", "TangTret", "A","ToaA"));
            countryList.add(new SearchData("BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", R.drawable.ic_home, "A05", "BM. Vật lý ứng dụng\n" + "PTN. Quang - Quang tử", "TangTret", "A","ToaA"));

            /**Tầng 1*/
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A11", "PTN. BM. Hóa hữu cơ","Tang1","A","ToaA"));
            countryList.add(new SearchData("BM. Hóa hữu cơ", R.drawable.ic_home, "A12", "BM. Hóa hữu cơ","Tang1","A","ToaA"));
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A13", "PTN. BM. Hóa hữu cơ","Tang1","A","ToaA"));

            /**Tầng 2*/
            countryList.add(new SearchData("PTN. Hợp chất và hóa dược", R.drawable.ic_home, "A21", "PTN. Hợp chất và hóa dược","Tang2","A","ToaA"));
            countryList.add(new SearchData("PTN. BM. Hóa hữu cơ", R.drawable.ic_home, "A22", "PTN. BM. Hóa hữu cơ","Tang2","A","ToaA"));

        }

        /**B*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("B.01_Phòng kế hoạch - tài chính", R.drawable.ic_home, "B01", "Phòng kế hoạch - tài chính", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.02_Phòng đào tạo", R.drawable.ic_home, "B02", "Phòng đào tạo", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.03", R.drawable.ic_home, "03", "B03", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.04_Phòng khảo thí và đảm bảo chất lượng", R.drawable.ic_home, "B04", "Phòng khảo thí và đảm bảo chất lượng", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.05_Văn phòng hiệu trưởng", R.drawable.ic_home, "B05", "Văn phòng hiệu trưởng", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.06_Phòng tổ chức - hành chính", R.drawable.ic_home, "B06", "Phòng tổ chức - hành chính", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.07_Phòng khoa học và công nghệ", R.drawable.ic_home, "B07", "Phòng khoa học và công nghệ", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.08_Phòng đào tạo sau đại học", R.drawable.ic_home, "B08", "Phòng đào tạo sau đại học", "TangTret", "B","ToaB"));
            countryList.add(new SearchData("B.09", R.drawable.ic_home, "B09", "", "TangTret", "B","ToaB"));

            /**Tầng 1*/
            countryList.add(new SearchData("B.11A", R.drawable.ic_home, "B11A", "", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.11B", R.drawable.ic_home, "B11B", "", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.13_BM. CNSH thực vật & chuyển hóa sinh học", R.drawable.ic_home, "B13", "BM. CNSH thực vật & chuyển hóa sinh học", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.14", R.drawable.ic_home, "B14", "", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.15", R.drawable.ic_home, "B15", "", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.16_PTN. Phân tích trung tâm", R.drawable.ic_home, "B16", "PTN. Phân tích trung tâm", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.17", R.drawable.ic_home, "B17", "", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.18_PTN. CNSH phân tử A", R.drawable.ic_home, "B18", "PTN. CNSH phân tử A", "Tang1", "B","ToaB"));
            countryList.add(new SearchData("B.19_PTN. CNSH phân tử A", R.drawable.ic_home, "B19", "PTN. CNSH phân tử A", "Tang1", "B","ToaB"));

            /**Tầng 2*/
            countryList.add(new SearchData("WC cbnv nữ (tầng 2 tòa B)", R.drawable.ic_home, "WC", "WC cbnv nữ", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.21_BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", R.drawable.ic_home, "B21", "BM. Sinh thái - Sinh học tiến hóa \n" + "PTN. Sinh môi \n" + "BM. Sinh môi học", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.22_BM. Di truyền học\n" + "PTN. Di truyền", R.drawable.ic_home, "B22", "BM. Di truyền học\n" + "PTN. Di truyền", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.23_BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", R.drawable.ic_home, "B23", "BM. CNSH thực vật & chuyển hóa sinh học\n" + "PTN. CNSH thực vật", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.24_BM. Hóa lý\n" + "PTN. Hóa xúc tác", R.drawable.ic_home, "B24", "BM. Hóa lý\n" + "PTN. Hóa xúc tác", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.25", R.drawable.ic_home, "B25", "", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.26_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "B26", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.27_BM. Hóa phân tích", R.drawable.ic_home, "B27", "BM. Hóa phân tích", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.28_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "B28", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", "Tang2", "B","ToaB"));
            countryList.add(new SearchData("B.29", R.drawable.ic_home, "B29", "", "Tang2", "B","ToaB"));

            /**Tầng 3*/
            countryList.add(new SearchData("WC cbnv nam (tầng 3 tòa B)", R.drawable.ic_home, "WC", "WC cbnv nam", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.30_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", R.drawable.ic_home, "B30", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng lớp học phương pháp luận sáng tạo", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.31_Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", R.drawable.ic_home, "B31", "Trung tâm sáng tạo khoa học kỹ thuật \n" + "Văn phòng", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.32_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", R.drawable.ic_home, "B32", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng thí nghiệm", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.33_BM. Hải dương - khí tượng - thủy văn", R.drawable.ic_home, "B33", "BM. Hải dương - khí tượng - thủy văn", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.34_BM. Vật lý địa cầu", R.drawable.ic_home, "B34", "BM. Vật lý địa cầu", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.35_BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", R.drawable.ic_home, "B35", "BM. Hải dương - khí tượng - thủy văn\n" + "Phòng học", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.36_Khoa vật lý\n" + "Phòng máy tính", R.drawable.ic_home, "B36", "Khoa vật lý\n" + "Phòng máy tính", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.37", R.drawable.ic_home, "B37", "", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.38_BM. Vật lý lý thuyết", R.drawable.ic_home, "B38", "BM. Vật lý lý thuyết", "Tang3", "B","ToaB"));
            countryList.add(new SearchData("B.39_BM. Vật lý địa cầu", R.drawable.ic_home, "B39", "BM. Vật lý địa cầu", "Tang3", "B","ToaB"));

            /**Tầng 4*/
            countryList.add(new SearchData("B.40a_Phòng máy tính 1", R.drawable.ic_home, "B40A", "Phòng máy tính 1", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.40", R.drawable.ic_home, "B40", "", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.41_Phòng học", R.drawable.ic_home, "B41", "Phòng học", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.42_Phòng học", R.drawable.ic_home, "B42", "Phòng học", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.43_Phòng học", R.drawable.ic_home, "B43", "Phòng học", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.44", R.drawable.ic_home, "B44", "", "Tang4", "B","ToaB"));
            countryList.add(new SearchData("B.45_Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", R.drawable.ic_home, "B45", "Văn phòng Chuyên san Khoa học tự nhiên\n" + "Tạp chí phát triển KH&CN ĐHQG-HCM", "Tang4", "B","ToaB"));

        }

        /**C*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("C05_Phòng chuyên đề", R.drawable.ic_home, "C05", "Phòng chuyên đề", "TangTret", "C","ToaC"));
            countryList.add(new SearchData("C04_PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển" + "PTN. Trầm tích học", R.drawable.ic_home, "C04", "PTN. Trưng bày mẫu vật \n" + "BM. Trầm tích & địa chất biển", "TangTret", "C","ToaC"));
            countryList.add(new SearchData("C03_BM. Địa chất thủy văn - địa chất công trình", R.drawable.ic_home, "C03", "BM. Địa chất thủy văn - địa chất công trình", "TangTret", "C","ToaC"));
            countryList.add(new SearchData("C02_BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", R.drawable.ic_home, "C02", "BM. Thạch học và khoáng sản\n" + "PTN. Khoáng vật và thạch học", "TangTret", "C","ToaC"));
            countryList.add(new SearchData("C01_BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", R.drawable.ic_home, "C01", "BM. Địa chất cơ sở\n" + "PTN. Tin học, GIS và viễn thám", "TangTret", "C","ToaC"));

            /**Tầng 1*/
            countryList.add(new SearchData("C15_Văn phòng khoa môi trường", R.drawable.ic_home, "C15", "Văn phòng khoa môi trường","Tang1","C","ToaC"));
            countryList.add(new SearchData("Khoa môi trường\n" + "Phòng chuyên đề", R.drawable.ic_home, "14AC", "Khoa môi trường\n" + "Phòng chuyên đề","Tang1","C","ToaC"));
            countryList.add(new SearchData("C14_PTN. Phân tích và kiểm soát ô nhiễm môi trường", R.drawable.ic_home, "C14", "PTN. Phân tích và kiểm soát ô nhiễm môi trường","Tang1","C","ToaC"));
            countryList.add(new SearchData("WC cbnv nữ (tầng 1 tòa C)", R.drawable.ic_home, "WC", "WC cbnv nữ","Tang1","C","ToaC"));
            countryList.add(new SearchData("C12A_Văn phòng khoa địa chất", R.drawable.ic_home, "C12A", "Văn phòng khoa địa chất","Tang1","C","ToaC"));
            countryList.add(new SearchData("Khoa địa chất", R.drawable.ic_home, "C12B", "Khoa địa chất","Tang1","C","ToaC"));
            countryList.add(new SearchData("C12_BM. Địa chất dầu khí", R.drawable.ic_home, "C12", "BM. Địa chất dầu khí","Tang1","C","ToaC"));
            countryList.add(new SearchData("C11_PTN. Địa hóa và địa chất môi trường", R.drawable.ic_home, "C11", "PTN. Địa hóa và địa chất môi trường","Tang1","C","ToaC"));


            /**Tầng 2*/
            countryList.add(new SearchData("C25_BM. Quản lý và tin học môi trường", R.drawable.ic_home, "C25", "BM. Quản lý và tin học môi trường","Tang2","C","ToaC"));
            countryList.add(new SearchData("C24", R.drawable.ic_home, "C24", "","Tang2","C","ToaC"));
            countryList.add(new SearchData("C23A_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "C23A", "Khoa CNTT \n" + "Phòng máy tính","Tang2","C","ToaC"));
            countryList.add(new SearchData("C23B_Khoa CNTT\n" + "Phòng máy tính", R.drawable.ic_home, "C23B", "Khoa CNTT\n" + "Phòng máy tính","Tang2","C","ToaC"));
            countryList.add(new SearchData("C22", R.drawable.ic_home, "C22", "","Tang2","C","ToaC"));
            countryList.add(new SearchData("C21_PTN. Tinh thể - ngọc học", R.drawable.ic_home, "C21", "PTN. Tinh thể - ngọc học","Tang2","C","ToaC"));

            /**Tầng 3*/
            countryList.add(new SearchData("C34_BM. Khoa học môi trường", R.drawable.ic_home, "C34", "BM. Khoa học môi trường","Tang3","C","ToaC"));
            countryList.add(new SearchData("C34A_BM. Công nghệ môi trường", R.drawable.ic_home, "C34A", "BM. Công nghệ môi trường","Tang3","C","ToaC"));
            countryList.add(new SearchData("C33", R.drawable.ic_home, "C33", "","Tang3","C","ToaC"));
            countryList.add(new SearchData("WC nữ (tầng 3 tòa C)", R.drawable.ic_home, "WC", "WC nữ","Tang3","C","ToaC"));
            countryList.add(new SearchData("C32B", R.drawable.ic_home, "C32B", "","Tang3","C","ToaC"));
            countryList.add(new SearchData("C32A", R.drawable.ic_home, "C32A", "","Tang3","C","ToaC"));
            countryList.add(new SearchData("C31", R.drawable.ic_home, "C31", "","Tang3","C","ToaC"));

            /**Tầng 4*/
            countryList.add(new SearchData("C44_Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán", R.drawable.ic_home, "C44", "Khoa CNTT\n" + "Phòng nghiên cứu sinh\n" + "Trung tâm ngôn ngữ học tính toán","Tang4","C","ToaC"));

            countryList.add(new SearchData("C43B", R.drawable.ic_home, "C43B", "","Tang4","C","ToaC"));
            countryList.add(new SearchData("C43A", R.drawable.ic_home, "C43A", "","Tang4","C","ToaC"));
            countryList.add(new SearchData("C42", R.drawable.ic_home, "C42", "","Tang4","C","ToaC"));
            countryList.add(new SearchData("C41", R.drawable.ic_home, "C41", "","Tang4","C","ToaC"));


        }

        /**D*/
        {
            /**Tầng Trệt*/
            countryList.add(new SearchData("Trung tâm khoa học và công nghệ sinh học", R.drawable.ic_home, "D01", "Trung tâm khoa học và công nghệ sinh học","TangTret", "D","ToaD"));

            /**Tầng 1*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("PTN. Thực vật_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "D11", "Phòng học\n" + "Phòng thí nghiệm","Tang1","D","ToaD"));
            countryList.add(new SearchData("PTN. Thực vật_Phòng nghiên cứu", R.drawable.ic_home, "D12", "Phòng nghiên cứu","Tang1","D","ToaD"));

            /**Tầng 2*/
            countryList.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng học\n" + "Phòng thí nghiệm", R.drawable.ic_home, "D21", "Phòng học\n" + "Phòng thí nghiệm","Tang2","D","ToaD"));
            countryList.add(new SearchData("PTN. Động vật\n" + "PTN. Sinh học đại cương_Phòng nghiên cứu", R.drawable.ic_home, "D22", "Phòng nghiên cứu","Tang2","D","ToaD"));

        }

        /**E*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("Phòng khoa học và công nghệ", R.drawable.ic_home, "E01", "Phòng khoa học và công nghệ", "TangTret", "E","ToaE"));
            countryList.add(new SearchData("Phòng y tế", R.drawable.ic_home, "E02", "Phòng y tế", "TangTret", "E","ToaE"));
            countryList.add(new SearchData("BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", R.drawable.ic_home, "E03", "BM. Sinh lý học và CNSH động vật \n" + "Phòng thực tập chuyên ngành sinh lý động vật", "TangTret", "E","ToaE"));
            countryList.add(new SearchData("PTN. Vi sinh - Sinh môi", R.drawable.ic_home, "E04", "PTN. Vi sinh - Sinh môi", "TangTret", "E","ToaE"));

            /**Tầng 1*/
            countryList.add(new SearchData("E101C_Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học", R.drawable.ic_home, "E101C", "Khoa ĐTVT\n" + "Phòng nghiên cứu khoa học","Tang1","E","ToaE"));
            countryList.add(new SearchData("E101A_Khoa ĐTVT\n" + "Phòng họp", R.drawable.ic_home, "E101A", "Khoa ĐTVT\n" + "Phòng họp","Tang1","E","ToaE"));
            countryList.add(new SearchData("E101B_PTN. DESLAB", R.drawable.ic_home, "E101B", "PTN. DESLAB","Tang1","E","ToaE"));
            countryList.add(new SearchData("WC nữ (tầng 1 tòa E)\n" + "VC - NLĐ", R.drawable.ic_home, "WC", "WC nữ\n" + "VC - NLĐ","Tang1","E","ToaE"));
            countryList.add(new SearchData("E102_Khoa điện tử - viễn thông\n" + "Phòng máy tính", R.drawable.ic_home, "E102", "Khoa điện tử - viễn thông\n" + "Phòng máy tính","Tang1","E","ToaE"));
            countryList.add(new SearchData("E103A_BM. Máy tính - hệ thống nhúng", R.drawable.ic_home, "E103A", "BM. Máy tính - hệ thống nhúng","Tang1","E","ToaE"));
            countryList.add(new SearchData("E103B_PTN. Máy tính - hệ thống nhúng", R.drawable.ic_home, "E103B", "PTN. Máy tính - hệ thống nhúng","Tang1","E","ToaE"));
            countryList.add(new SearchData("E104_PTN. Viễn thông", R.drawable.ic_home, "E104", "PTN. Viễn thông","Tang1","E","ToaE"));
            countryList.add(new SearchData("PTN. Điện tử", R.drawable.ic_home, "E105A", "PTN. Điện tử","Tang1","E","ToaE"));
            countryList.add(new SearchData("BM. Điện tử", R.drawable.ic_home, "E105B", "BM. Điện tử","Tang1","E","ToaE"));
            countryList.add(new SearchData("E106_BM. Viễn thông và mạng", R.drawable.ic_home, "E106", "BM. Viễn thông và mạng","Tang1","E","ToaE"));
            countryList.add(new SearchData("E107_Văn phòng khoa điện tử - viễn thông", R.drawable.ic_home, "E107", "Văn phòng khoa điện tử - viễn thông" ,"Tang1","E","ToaE"));
            /**Tầng 2*/
            countryList.add(new SearchData("E201B", R.drawable.ic_home, "E201B", "","Tang2","E","ToaE"));
            countryList.add(new SearchData("E201A_BM. Giải tích", R.drawable.ic_home, "E201A", "BM. Giải tích","Tang2","E","ToaE"));
            countryList.add(new SearchData("WC nam (tầng 2 tòa E)", R.drawable.ic_home, "WC", "WC nam","Tang2","E","ToaE"));
            countryList.add(new SearchData("E202B_Phòng máy vi tính", R.drawable.ic_home, "E202B", "Phòng máy vi tính","Tang2","E","ToaE"));
            countryList.add(new SearchData("E202A_BM. Ứng dụng tin học", R.drawable.ic_home, "E202A", "BM. Ứng dụng tin học","Tang2","E","ToaE"));
            countryList.add(new SearchData("E203_BM. Vật lý chất rắn", R.drawable.ic_home, "E203", "BM. Vật lý chất rắn","Tang2","E","ToaE"));
            countryList.add(new SearchData("E204", R.drawable.ic_home, "E204", "","Tang2","E","ToaE"));
            countryList.add(new SearchData("E205_BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng", R.drawable.ic_home, "E205", "BM. Vật lý tin học\n" + "PTN. Vi điều khiển - hệ thống nhúng","Tang2","E","ToaE"));
            countryList.add(new SearchData("L2.P6", R.drawable.ic_home, "L2.P6", "","Tang2","E","ToaE"));

            /**Tầng 3*/
            countryList.add(new SearchData("E301", R.drawable.ic_home, "E301", "","Tang3","E","ToaE"));
            countryList.add(new SearchData("WC nữ (tầng 3 tòa E)", R.drawable.ic_home, "WC", "WC nữ","Tang3","E","ToaE"));
            countryList.add(new SearchData("E302", R.drawable.ic_home, "E302", "","Tang3","E","ToaE"));
            countryList.add(new SearchData("E303A_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử", R.drawable.ic_home, "E303A", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý điện tử","Tang3","E","ToaE"));
            countryList.add(new SearchData("E303B_Phòng thực tập điện tử", R.drawable.ic_home, "E303B", "Phòng thực tập điện tử","Tang3","E","ToaE"));
            countryList.add(new SearchData("E304_Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học", R.drawable.ic_home, "E304", "Khoa vật lý và vật lý kỹ thuật\n" + "BM. Vật lý tin học","Tang3","E","ToaE"));
            countryList.add(new SearchData("BM. Vật lý tin học\n" + "Phòng máy tính", R.drawable.ic_home, "E304A", "BM. Vật lý tin học\n" + "Phòng máy tính","Tang3","E","ToaE"));
            countryList.add(new SearchData("E305_PTN. Thiết kế vi mạch và hệ thống nhúng", R.drawable.ic_home, "E305", "PTN. Thiết kế vi mạch và hệ thống nhúng","Tang3","E","ToaE"));
            countryList.add(new SearchData("E306_BM. Vật lý tin học\n" + "PTN chuyên đề", R.drawable.ic_home, "E306", "BM. Vật lý tin học\n" + "PTN chuyên đề","Tang3","E","ToaE"));

            /**Tầng 4*/
            countryList.add(new SearchData("E401", R.drawable.ic_home, "E401", "","Tang4","E","ToaE"));
            countryList.add(new SearchData("WC nam (tầng 4 tòa E)", R.drawable.ic_home, "WC", "WC nam","Tang4","E","ToaE"));
            countryList.add(new SearchData("E402", R.drawable.ic_home, "E402", "","Tang4","E","ToaE"));
            countryList.add(new SearchData("E403", R.drawable.ic_home, "E403", "","Tang4","E","ToaE"));
            countryList.add(new SearchData("E404", R.drawable.ic_home, "E404", "","Tang4","E","ToaE"));
            countryList.add(new SearchData("E405", R.drawable.ic_home, "E405", "","Tang4","E","ToaE"));
            countryList.add(new SearchData("E406", R.drawable.ic_home, "E406", "","Tang4","E","ToaE"));


        }

        /**F*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("WC nữ (tầng trệt tòa F)", R.drawable.ic_home, "WC1", "WC nữ","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.01_Phòng tổ chức hành chính", R.drawable.ic_home, "F01", "Phòng tổ chức hành chính","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.02_Phòng thông tin - truyền thông", R.drawable.ic_home, "F02", "Phòng thông tin - truyền thông","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.03_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "F03", "BM. Di truyền\n" + "PTN. Sinh học phân tử","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.04_BM. Di truyền\n" + "PTN. Sinh học phân tử", R.drawable.ic_home, "F04", "BM. Di truyền\n" + "PTN. Sinh học phân tử","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.06_Văn phòng khoa sinh học - công nghệ sinh học", R.drawable.ic_home, "F06", "Văn phòng khoa sinh học - công nghệ sinh học","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.07_Phòng kế hoạch - tài chính", R.drawable.ic_home, "F07", "Phòng kế hoạch - tài chính","TangTret","F","ToaF"));
            countryList.add(new SearchData("WC cbvc nữ (tầng trệt tòa F)", R.drawable.ic_home, "WC2", "WC cbvc nữ","TangTret","F","ToaF"));
            countryList.add(new SearchData("Tổ giảng đường", R.drawable.ic_home, "F07A", "Tổ giảng đường","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.08_Văn phòng khoa toán - tin học", R.drawable.ic_home, "F08", "Văn phòng khoa toán - tin học","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.09_Khoa toán - tin học", R.drawable.ic_home, "F09", "Khoa toán - tin học","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.10_Trung tâm khoa học - toán học", R.drawable.ic_home, "F10", "Trung tâm khoa học - toán học","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.11_BM. Cơ học", R.drawable.ic_home, "F11", "BM. Cơ học","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.12_BM. Xác suất thống kê", R.drawable.ic_home, "F12", "BM. Xác suất thống kê","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.13_BM. Tối ưu & hệ thống", R.drawable.ic_home, "F13", "BM. Tối ưu & hệ thống","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.14_BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không", R.drawable.ic_home, "F14", "BM. Vật lý ứng dụng\n" + "PTN. Vật lý chân không","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.15_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "F15", "PTN. Vật liệu kỹ thuật cao","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.16_PTN. Tổng hợp & phân tích vật liệu màng mỏng", R.drawable.ic_home, "F16", "PTN. Tổng hợp & phân tích vật liệu màng mỏng","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.17_PTN. Vật liệu kỹ thuật cao", R.drawable.ic_home, "F17", "PTN. Vật liệu kỹ thuật cao","TangTret","F","ToaF"));
            countryList.add(new SearchData("WC nam (tầng trệt tòa F)", R.drawable.ic_home, "WC3", "WC nam","TangTret","F","ToaF"));
            countryList.add(new SearchData("F.18_PTN. Tổng hợp vật liệu màng mỏng", R.drawable.ic_home, "F18", "PTN. Tổng hợp vật liệu màng mỏng","TangTret","F","ToaF"));

            /**Tầng 1*/

            countryList.add(new SearchData("WC nam (tầng 1 tòa F)", R.drawable.ic_home, "WC", "WC nam","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.100_BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường", R.drawable.ic_home, "F100", "BM. CNSH phân tử - môi trường\n" + "PTN. BM. CNSH phân tử - môi trường","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.101", R.drawable.ic_home, "F101", "","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.101A_Phòng quan hệ đối ngoại", R.drawable.ic_home, "F101A", "Phòng quan hệ đối ngoại","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.101B_PTN. CNSH phân tử (B)", R.drawable.ic_home, "F101B", "PTN. CNSH phân tử (B)","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.102_Phòng họp", R.drawable.ic_home, "F102", "Phòng họp","Tang1","F","ToaF"));
            countryList.add(new SearchData("Rest room", R.drawable.ic_home, "F102A", "rest room","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.103_Văn phòng hội đồng trường", R.drawable.ic_home, "F103", "Văn phòng hội đồng trường","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.104", R.drawable.ic_home, "F104", "","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.105_Phòng quan hệ đối ngoại", R.drawable.ic_home, "F105", "Phòng quan hệ đối ngoại","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.106A_Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh", R.drawable.ic_home, "F106A", "Văn phòng ban chỉ huy quân sự \n" + "Văn phòng đảng ủy \n" + "Văn phòng hội cựu chiến binh","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.106B_Văn phòng công đoàn", R.drawable.ic_home, "F106B", "Văn phòng công đoàn","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.107_Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế", R.drawable.ic_home, "F107", "Văn phòng tiếp công dân \n" + "Phòng thanh tra pháp chế","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.108_Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên", R.drawable.ic_home, "F108", "Văn phòng đoàn thanh niên \n" + "Văn phòng hội sinh viên","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.109_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "F109", "Phòng máy\n" + "Khoa CNTT","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.110_Phòng máy\n" + "Khoa CNTT", R.drawable.ic_home, "F110", "Phòng máy\n" + "Khoa CNTT","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.111", R.drawable.ic_home, "F111", "","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.112A_Khoa khoa học và công nghệ vật liệu", R.drawable.ic_home, "F112A", "Khoa khoa học và công nghệ vật liệu","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.112_BM. Sinh lý học và CNSH động vật", R.drawable.ic_home, "F112", "BM. Sinh lý học và CNSH động vật","Tang1","F","ToaF"));
            countryList.add(new SearchData("F.113_Văn phòng khoa Khoa khoa học và công nghệ vật  liệu", R.drawable.ic_home, "F113", "Văn phòng khoa Khoa khoa học và công nghệ vật  liệu","Tang1","F","ToaF"));

            /**Tầng 2*/
            countryList.add(new SearchData("Khoa môi trường - phòng đọc", R.drawable.ic_home, "F200A", "Khoa môi trường - phòng đọc","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.200_Khoa môi trường\n" + "PTN. Tin học môi trường", R.drawable.ic_home, "F200", "Khoa môi trường\n" + "PTN. Tin học môi trường","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.202", R.drawable.ic_home, "F202", "","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.203", R.drawable.ic_home, "F203", "","Tang2","F","ToaF"));
            countryList.add(new SearchData("University council - office of the chair", R.drawable.ic_home, "F204", "University council - office of the chair","Tang2","F","ToaF"));
            countryList.add(new SearchData("F205A", R.drawable.ic_home, "F205A", "","Tang2","F","ToaF"));
            countryList.add(new SearchData("F205B", R.drawable.ic_home, "F205B", "","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.206A_BM. Tài chính định lượng", R.drawable.ic_home, "F206A", "BM. Tài chính định lượng","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.206_BM. Giáo dục toán học", R.drawable.ic_home, "F206", "BM. Giáo dục toán học","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.207", R.drawable.ic_home, "F207", "","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.208_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "F208", "Phòng máy\n" + "Khoa toán - tin học","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.209_Phòng máy\n" + "Khoa toán - tin học", R.drawable.ic_home, "F209", "Phòng máy\n" + "Khoa toán - tin học","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.210_BM. Đại số", R.drawable.ic_home, "F210", "BM. Đại số","Tang2","F","ToaF"));
            countryList.add(new SearchData("F.211_PTN. Tổng hợp vật liệu polyme", R.drawable.ic_home, "F211", "PTN. Tổng hợp vật liệu polyme","Tang2","F","ToaF"));

            /**Tầng 3*/
            countryList.add(new SearchData("F.300", R.drawable.ic_home, "F300", "","Tang3","F","ToaF"));
            countryList.add(new SearchData("F.301_Phòng học", R.drawable.ic_home, "F301", "Phòng học","Tang3","F","ToaF"));
            countryList.add(new SearchData("F.302", R.drawable.ic_home, "F302", "","Tang3","F","ToaF"));
            countryList.add(new SearchData("F.303_Phòng học", R.drawable.ic_home, "F303", "Phòng học","Tang3","F","ToaF"));
            countryList.add(new SearchData("F.304_Phòng học", R.drawable.ic_home, "F304", "Phòng học","Tang3","F","ToaF"));
//                countryList.add(new SearchData("F.305_Phòng học", R.drawable.ic_home, "305", "Phòng học","Tang3","F","ToaF"));
            countryList.add(new SearchData("BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh", R.drawable.ic_home, "F305a", "BM. Vật liệu màng mỏng \n" + "BM. Vật liệu từ & y sinh","Tang3","F","ToaF"));
            countryList.add(new SearchData("F.305_PTN. Phân tích vật liệu", R.drawable.ic_home, "F305", "PTN. Phân tích vật liệu","Tang3","F","ToaF"));

            /**Tầng 4*/
            countryList.add(new SearchData("F.400_PTN. Phân tích vật liệu polyme", R.drawable.ic_home, "F400", "PTN. Phân tích vật liệu polyme","Tang4","F","ToaF"));


        }

        /**H*/
        {
            /**Tầng Trệt*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("H2.2", R.drawable.ic_home, "H22", "", "TangTret", "H","ToaH"));
            countryList.add(new SearchData("H2.1", R.drawable.ic_home, "H21", "", "TangTret", "H","ToaH"));

            /**Tầng 1*/
            countryList.add(new SearchData("H2.3", R.drawable.ic_home, "H23", "","Tang1","H","ToaH"));

        }

        /**I*/
        {

            /**Tầng 1*/
            //countryList = new ArrayList<>();
            countryList.add(new SearchData("Hội trường I", R.drawable.ic_home, "HoiTruongI", "Hội trường I", "Tang1", "I","ToaI"));
            countryList.add(new SearchData("I.12_Phòng họp", R.drawable.ic_home, "I12", "Phòng họp", "Tang1", "I","ToaI"));
            countryList.add(new SearchData("I.11_Phòng họp", R.drawable.ic_home, "I11", "Phòng họp", "Tang1", "I","ToaI"));
            countryList.add(new SearchData("WC (tầng 1 tòa I)", R.drawable.ic_home, "WC", "WC1","Tang1","I","ToaI"));


            /**Tầng 2*/
            countryList.add(new SearchData("I.25_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "I25", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","Tang2","I","ToaI"));
            countryList.add(new SearchData("I.26", R.drawable.ic_home, "I26", "","Tang2","I","ToaI"));
            countryList.add(new SearchData("I.27_BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu", R.drawable.ic_home, "I27", "BM. Hóa vô cơ và ứng dụng \n" + "Phòng nghiên cứu","Tang2","I","ToaI"));
            countryList.add(new SearchData("I.24", R.drawable.ic_home, "I24", "","Tang2","I","ToaI"));
            countryList.add(new SearchData("I.23", R.drawable.ic_home, "I23", "","Tang2","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 2 tòa I)", R.drawable.ic_home, "WC", "WC2","Tang2","I","ToaI"));

            /**Tầng 3*/
            countryList.add(new SearchData("I.35_PTN. Hóa vô cơ chuyên ngành", R.drawable.ic_home, "I35", "PTN. Hóa vô cơ chuyên ngành","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.36_BM. Hóa vô cơ và ứng dụng", R.drawable.ic_home, "I36", "BM. Hóa vô cơ và ứng dụng","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.37_Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng", R.drawable.ic_home, "I37", "Văn phòng bộ môn \n" + " BM. Hóa vô cơ & ứng dụng","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.38_Phòng hội thảo", R.drawable.ic_home, "I38", "Phòng hội thảo","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.31", R.drawable.ic_home, "I31", "","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.32", R.drawable.ic_home, "I32", "","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.33", R.drawable.ic_home, "I33", "","Tang3","I","ToaI"));
            countryList.add(new SearchData("I.34", R.drawable.ic_home, "I34", "","Tang3","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 3 tòa I)", R.drawable.ic_home, "WC", "WC3","Tang3","I","ToaI"));

            /**Tầng 4*/
            countryList.add(new SearchData("I.46_PTN. Hóa dược", R.drawable.ic_home, "I46", "PTN. Hóa dược","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.47_BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành", R.drawable.ic_home, "I47", "BM. Hóa phân tích\n" + "PTN. Hóa phân tích chuyên ngành","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.48_BM. Hóa phân tích", R.drawable.ic_home, "I48", "BM. Hóa phân tích","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.44_APCS-404", R.drawable.ic_home, "I44", "APCS-404","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.43_Văn phòng các chương trình đào tạo đặc biệt ngành CNTT", R.drawable.ic_home, "I43", "Văn phòng các chương trình đào tạo đặc biệt ngành CNTT","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.42_APCS-402", R.drawable.ic_home, "I42", "APCS-402","Tang4","I","ToaI"));
            countryList.add(new SearchData("I.41_APCS-401", R.drawable.ic_home, "I41", "APCS-401","Tang4","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 4 tòa I)", R.drawable.ic_home, "WC", "WC4","Tang4","I","ToaI"));


            /**Tầng 5*/
            countryList.add(new SearchData("I.56_PTN. Hóa lý ứng dụng", R.drawable.ic_home, "I56", "PTN. Hóa lý ứng dụng","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.57_BM. Hóa lý\n" + "PTN. Hóa nano", R.drawable.ic_home, "I57", "BM. Hóa lý\n" + "PTN. Hóa nano","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.58_BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ", R.drawable.ic_home, "I58", "BM. Hóa lý\n" + "PTN. Hóa lý hữu cơ","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.59_BM. Hóa dược", R.drawable.ic_home, "I59", "BM. Hóa dược","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.59A_Phòng hội thảo", R.drawable.ic_home, "I59A", "Phòng hội thảo","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.59B_Văn phòng khoa", R.drawable.ic_home, "I59B", "Văn phòng khoa","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.55_Phòng họp", R.drawable.ic_home, "I55", "Phòng họp","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.54_Văn phòng khoa CNTT", R.drawable.ic_home, "I54", "Văn phòng khoa CNTT","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.53_Bộ phận giáo vụ\n" + "Trợ lý sinh viên", R.drawable.ic_home, "I53", "Bộ phận giáo vụ\n" + "Trợ lý sinh viên","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.52_Phòng máy tính", R.drawable.ic_home, "I52", "Phòng máy tính","Tang5","I","ToaI"));
            countryList.add(new SearchData("I.51_Bộ phận kỹ thuật", R.drawable.ic_home, "I51", "Bộ phận kỹ thuật","Tang5","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 5 tòa I)", R.drawable.ic_home, "WC", "WC5","Tang5","I","ToaI"));

            /**Tầng 6*/
            countryList.add(new SearchData("I.65_BM. Hóa học cao phân tử\n" + "Polyme 1", R.drawable.ic_home, "I65", "BM. Hóa học cao phân tử\n" + "Polyme 1", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.66_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "I66", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.67_BM. Hóa lý\n" + "PTN. Hóa lý thuyết", R.drawable.ic_home, "I67", "BM. Hóa lý\n" + "PTN. Hóa lý thuyết", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.68_BM. Hóa lý\n" + "PTN. Điện hóa", R.drawable.ic_home, "I68", "BM. Hóa lý\n" + "PTN. Điện hóa", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.69_BM. Hóa học cao phân tử\n" + "PTN. Polyme 2" , R.drawable.ic_home, "I69", "BM. Hóa học cao phân tử\n" + "PTN. Polyme 2", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.69A_Văn phòng bộ môn", R.drawable.ic_home, "I69A", "Văn phòng bộ môn", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.64_PTN. Trí tuệ nhân tạo", R.drawable.ic_home, "I64", "PTN. Trí tuệ nhân tạo", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.63_BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", R.drawable.ic_home, "I63", "BM. Tin học cơ sở \n " + "BM. Công nghệ tri thức", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.62_Phòng máy tính", R.drawable.ic_home, "I62", "Phòng máy tính", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("I.61_Phòng máy tính", R.drawable.ic_home, "I61", "Phòng máy tính", "Tang6", "I","ToaI"));
            countryList.add(new SearchData("WC (tầng 6 tòa I)", R.drawable.ic_home, "WC", "WC6","Tang6","I","ToaI"));

            /**Tầng 7*/
            countryList.add(new SearchData("I.76_Phòng họp sinh viên", R.drawable.ic_home, "I76", "Phòng họp sinh viên","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.77_PTN. Hóa tin", R.drawable.ic_home, "I77", "PTN. Hóa tin","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.78_PTN. Hóa tin", R.drawable.ic_home, "I78", "PTN. Hóa tin","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.79", R.drawable.ic_home, "I79", "","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.79A", R.drawable.ic_home, "I79A", "","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.75", R.drawable.ic_home, "I75", "","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.74_BM. Mạng máy tính và viễn thông", R.drawable.ic_home, "I74", "BM. Mạng máy tính và viễn thông","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.73_PTN. An ninh mạng", R.drawable.ic_home, "I73", "PTN. An ninh mạng","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.72_BM. Thị giác máy tính và khoa học robot", R.drawable.ic_home, "I72", "BM. Thị giác máy tính và khoa học robot","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.72A_Phòng họp", R.drawable.ic_home, "I72A", "Phòng họp","Tang7","I","ToaI"));
            countryList.add(new SearchData("I.71_Phòng máy tính", R.drawable.ic_home, "I71", "Phòng máy tính","Tang7","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 7 tòa I)", R.drawable.ic_home, "WC", "WC7","Tang7","I","ToaI"));

            /**Tầng 8*/
            countryList.add(new SearchData("I.86_PTN. Hệ thống nhúng", R.drawable.ic_home, "I86", "PTN. Hệ thống nhúng","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.87_PTN. Công nghệ phần mềm", R.drawable.ic_home, "I87", "PTN. Công nghệ phần mềm","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.87A_PTN. Hệ thống di động & media", R.drawable.ic_home, "I87A", "PTN. Hệ thống di động & media","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.88", R.drawable.ic_home, "I88", "","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.89_Phòng vật lý tính toán", R.drawable.ic_home, "I89", "Phòng vật lý tính toán","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.85_Phòng thông tin - truyền thông", R.drawable.ic_home, "I85", "Phòng thông tin - truyền thông","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.84_BM. Hệ thống thông tin", R.drawable.ic_home, "I84", "BM. Hệ thống thông tin","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.82_BM. Công nghệ phần mềm", R.drawable.ic_home, "I82", "BM. Công nghệ phần mềm","Tang8","I","ToaI"));
            countryList.add(new SearchData("I.81_BM. Khoa học máy tính", R.drawable.ic_home, "I81", "BM. Khoa học máy tính","Tang8","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 8 tòa I)", R.drawable.ic_home, "WC", "WC8","Tang8","I","ToaI"));

            /**Tầng 9*/
            countryList.add(new SearchData("I.91", R.drawable.ic_home, "I91", "","Tang9","I","ToaI"));
            countryList.add(new SearchData("I.92", R.drawable.ic_home, "I92", "","Tang9","I","ToaI"));
            countryList.add(new SearchData("Thư viện (tầng 9)", R.drawable.ic_home, "ThuVien", "Thư viện","Tang9","I","ToaI"));

            /**Tầng 10*/
            countryList.add(new SearchData("Phòng luận văn", R.drawable.ic_home, "LuanVan", "Phòng luận văn","Tang10","I","ToaI"));
            countryList.add(new SearchData("Thư viện (tầng 10)", R.drawable.ic_home, "ThuVien", "Thư viện","Tang10","I","ToaI"));

            /**Tầng 11*/
            countryList.add(new SearchData("I.11E", R.drawable.ic_home, "I11E", "","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11F", R.drawable.ic_home, "I11F", "","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11G2", R.drawable.ic_home, "I11G2", "","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11G1", R.drawable.ic_home, "I11G1", "","Tang11","I","ToaI"));
            countryList.add(new SearchData("ITEC office", R.drawable.ic_home, "ITEC", "ITEC office","Tang11","I","ToaI"));
            countryList.add(new SearchData("Phòng thông tin truyền thông (tầng 11)", R.drawable.ic_home, "I11D", "Phòng thông tin truyền thông","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11C_Phòng học", R.drawable.ic_home, "I11C", "Phòng học","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11B_Phòng học", R.drawable.ic_home, "I11B", "Phòng học","Tang11","I","ToaI"));
            countryList.add(new SearchData("I.11A_Phòng học", R.drawable.ic_home, "I11A", "Phòng học","Tang11","I","ToaI"));
            countryList.add(new SearchData("WC (tầng 11 tòa I)", R.drawable.ic_home, "WC", "WC11","Tang11","I","ToaI"));

            /**Tầng 12*/
            countryList.add(new SearchData("I.12C", R.drawable.ic_home, "I12C", "","Tang12","I","ToaI"));
            countryList.add(new SearchData("I.12B_Khoa ĐTVT\n" + "PTN CLC 2", R.drawable.ic_home, "I12B", "Khoa ĐTVT\n" + "PTN CLC 2","Tang12","I","ToaI"));
            countryList.add(new SearchData("I.12A", R.drawable.ic_home, "I12A", "","Tang12","I","ToaI"));

        }

        /**GD1*/
        {
            countryList.add(new SearchData("Giảng Đường 1", R.drawable.ic_home, "", "Giảng Đường 1", "TangTret", "GD1"));
        }

        /**GD2*/
        {
            countryList.add(new SearchData("Giảng Đường 2", R.drawable.ic_home, "", "Giảng Đường 2", "TangTret", "GD2"));
        }

        /**TTTH*/
        {
            countryList.add(new SearchData("Trung Tâm Tin Học", R.drawable.ic_home, "", "Trung Tâm Tin Học", "TangTret", "TTTH"));

        }
    }

}

