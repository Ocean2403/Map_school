package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeptDetail extends AppCompatActivity {

    FirebaseFirestore db;

    public Toolbar toolbar;

    private LinkedHashMap<String, DeptFloor> subjects = new LinkedHashMap<String, DeptFloor>();
    private ArrayList<DeptFloor> deptList = new ArrayList<DeptFloor>();

    private DeptDetailAdapter listAdapter;
    private ExpandableListView listView;

    DeptRoom detailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept_detail);

        toolbar = findViewById(R.id.app_bar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeptDetail.this, Department.class));
            }
        });

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String deptName = intent.getStringExtra("deptName");

        Toolbar toolbar = findViewById(R.id.app_bar);
        toolbar.setTitle(deptName);

        switch (deptName) {
            case "Tòa A":
                loadDataA();
                break;
            case "Tòa B":
                loadDataB();
                break;
            case "Tòa C":
                loadDataC();
                break;
            case "Tòa D":
                loadDataD();
                break;
            case "Tòa E":
                loadDataE();
                break;
            case "Tòa F":
                loadDataF();
                break;
            case "Tòa H":
                loadDataH();
                break;
            case "Tòa I":
                loadDataI();
                break;
        }

        //get reference of the ExpandableListView
        listView = (ExpandableListView) findViewById(R.id.expandableList);
        // create the adapter by passing your ArrayList data
        listAdapter = new DeptDetailAdapter(DeptDetail.this, deptList);
        // attach the adapter to the expandable list view
        listView.setAdapter(listAdapter);

        collapseAll();

        // setOnChildClickListener listener for child row click
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                DeptFloor headerInfo = deptList.get(groupPosition);
                //get the child info
                detailInfo =  headerInfo.getList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(),  headerInfo.getFloor() + " - Phòng: "
                        + detailInfo.getRoom(), Toast.LENGTH_SHORT).show();

                showDialog();

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Đánh giá về phòng học");

                // Set up the input
                final EditText input = new EditText(mContext);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Gửi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();

                        Map<String, Object> feedback = new HashMap<>();
                        feedback.put("Phong", detailInfo.getRoom());
                        feedback.put("MoTa", m_Text);

                        db.collection("DanhGia")
                                .add(feedback)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(getBaseContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getBaseContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                */

                return false;
            }
        });

        // setOnGroupClickListener listener for group heading click
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                DeptFloor floor = deptList.get(groupPosition);
                return false;
            }
        });
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(DeptDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.feedback_dialog);

        final TextView deptName = dialog.findViewById(R.id.dept_name);
        final TextView datetime = dialog.findViewById(R.id.datetime);
        final EditText input = dialog.findViewById(R.id.input);
        Button send = dialog.findViewById(R.id.send);
        Button cancel = dialog.findViewById(R.id.cancel);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  'Ngày' dd-MM-yyyy");
        String currentDateTime = sdf.format(new Date());

        deptName.setText(detailInfo.getRoom());
        datetime.setText(currentDateTime);

        dialog.show();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();

                if (text.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Map<String, Object> feedback = new HashMap<>();
                    feedback.put("MoTa", text);
                    feedback.put("Phong", detailInfo.getRoom());
                    feedback.put("ThoiGian", currentDateTime);
                    db.collection("DanhGia")
                            .add(feedback)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(getBaseContext(), "Đánh giá thành công", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getBaseContext(), "Đánh giá thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });
                    dialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            listView.collapseGroup(i);
        }
    }


    //here we maintain our products in various departments
    private int addProduct(String department, String product){
        int groupPosition = 0;

        //check the hash map if the group already exists
        DeptFloor headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new DeptFloor();
            headerInfo.setFloor(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<DeptRoom> productList = headerInfo.getList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        DeptRoom detailInfo = new DeptRoom();
        detailInfo.setRoom(product);
        productList.add(detailInfo);
        headerInfo.setList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

    //load some initial data into out list
    private void loadDataA(){
        addProduct("Tầng trệt","A.01 - Văn phòng khoa Vật lý - Vật lý kỹ thuật");
        addProduct("Tầng trệt","A.02 - Phòng công tác sinh viên");
        addProduct("Tầng trệt","A.03 - Phòng phó hiệu trưởng");
        addProduct("Tầng trệt","A.04 - Phòng quản trị thiết bị");
        addProduct("Tầng trệt","BM. Vật lý ứng dụng\nPTN. Quang - Quang tử");

        addProduct("Tầng 1","PTN. BM. Hóa hữu cơ");
        addProduct("Tầng 1","BM. Hóa hữu cơ");
        addProduct("Tầng 1","PTN. BM. Hóa hữu cơ");

        addProduct("Tầng 2","PTN. BM. Hóa hữu cơ");
        addProduct("Tầng 2","PTN. Hợp chất và hóa dược");
        //addProduct("Tầng 2","PTN. Hợp chất và hóa dược");
    }


    private void loadDataB(){
        addProduct("Tầng trệt","01 - Phòng kế toán");
        addProduct("Tầng trệt","02 - Phòng đào tạo");
        addProduct("Tầng trệt","03");
        addProduct("Tầng trệt","04 - Phòng khảo thí và đảm bảo chất lượng");
        addProduct("Tầng trệt","05 - Văn phòng hiệu trưởng");
        addProduct("Tầng trệt","06 - Phòng tổ chức - hành chính");
        addProduct("Tầng trệt","07 - Phòng khoa học và công nghệ");
        addProduct("Tầng trệt","08 - Phòng đào tạo sau đại học");
        addProduct("Tầng trệt","09");

        addProduct("Tầng 1","11A");
        addProduct("Tầng 1","11B");
        addProduct("Tầng 1","13 - BM. CNSH thực vật & chuyển hóa sinh học");
        addProduct("Tầng 1","14");
        addProduct("Tầng 1","15");
        addProduct("Tầng 1","16 - PTN. Phân tích trung tâm");
        addProduct("Tầng 1","17");
        addProduct("Tầng 1","18 - PTN. CNSH phân tử A");
        addProduct("Tầng 1","19 - PTN. CNSH phân tử A");

        addProduct("Tầng 2","WC CBNV nữ");
        addProduct("Tầng 2","21 - BM. Sinh thái - Sinh học tiến hóa\nPTN. Sinh môi\nBM. Sinh môi học");
        addProduct("Tầng 2","22 - BM. Di truyền học\nPTN. Di truyền");
        addProduct("Tầng 2","23 - BM. CNSH thực vật & chuyển hóa sinh học\nPTN. CNSH thực vật");
        addProduct("Tầng 2","24 - BM. Hóa lý\nPTN. Hóa xúc tác");
        addProduct("Tầng 2","25");
        addProduct("Tầng 2","26 - BM. Hóa phân tích\nPTN. Hóa phân tích chuyên ngành");
        addProduct("Tầng 2","27 - BM. Hóa phân tích");
        addProduct("Tầng 2","28 - BM. Hóa phân tích\nPTN. Hóa phân tích chuyên ngành");
        addProduct("Tầng 2","29");

        addProduct("Tầng 3","WC CBNV nam");
        addProduct("Tầng 3","30 - Trung tâm sáng tạo khoa học kỹ thuật\nVăn phòng lớp học phương pháp luận sáng tạo");
        addProduct("Tầng 3","31 - Trung tâm sáng tạo khoa học kỹ thuật\nVăn phòng");
        addProduct("Tầng 3","32 - BM. Hải dương - Khí tượng - Thủy văn\nPhòng thí nghiệm");
        addProduct("Tầng 3","33 - BM. Hải dương - Khí tượng - Thủy văn");
        addProduct("Tầng 3","34 - BM. Vật lý địa cầu");
        addProduct("Tầng 3","35 - BM. Hải dương - Khí tượng - Thủy văn\nPhòng học");
        addProduct("Tầng 3","36 - Khoa vật lý\nPhòng máy tính");
        addProduct("Tầng 3","37");
        addProduct("Tầng 3","38 - BM. Vật lý lý thuyết");
        addProduct("Tầng 3","39 - BM. Vật lý địa cầu");

        addProduct("Tầng 4","B.40a - Phòng máy tính 1");
        addProduct("Tầng 4","40");
        addProduct("Tầng 4","B41 - Phòng học");
        addProduct("Tầng 4","B42 - Phòng học");
        addProduct("Tầng 4","B43 - Phòng học");
        addProduct("Tầng 4","44");
        addProduct("Tầng 4","45 - Văn phòng Chuyên san Khoa học tự nhiên\nTạp chí phát triển KH&CN ĐHQG-HCM");
    }


    private void loadDataC(){
        addProduct("Tầng trệt","C01 - BM. Địa chất cơ sở\nPTN. Tin học, GIS và viễn thám");
        addProduct("Tầng trệt","C02 - BM. Thạch học và khoáng sản\nPTN. Khoáng vật và thạch học");
        addProduct("Tầng trệt","C03 - BM. Địa chất thủy văn - Địa chất công trình");
        addProduct("Tầng trệt","C04 - PTN. Trưng bày mẫu vật\nBM. Trầm tích & Địa chất biển\nPTN. Trầm tích học");
        addProduct("Tầng trệt","C05 - Phòng chuyên đề");

        addProduct("Tầng 1","C11 - PTN. Địa hóa và địa chất môi trường");
        addProduct("Tầng 1","C12 - BM. Địa chất dầu khí");
        addProduct("Tầng 1","12A - Văn phòng khoa địa chất");
        addProduct("Tầng 1","WC CBNV nữ");
        addProduct("Tầng 1","C14 - PTN. Phân tích và kiểm soát ô nhiễm môi trường");
        addProduct("Tầng 1","Khoa môi trường\nPhòng chuyên đề");
        addProduct("Tầng 1","C15 - Văn phòng khoa môi trường");

        addProduct("Tầng 2","C21 - PTN. Tinh thể - Ngọc học");
        addProduct("Tầng 2","C22");
        addProduct("Tầng 2","C23A - Khoa Công nghệ thông tin\nPhòng máy tính");
        addProduct("Tầng 2","C23B - Khoa Công nghệ thông tin\nPhòng máy tính");
        addProduct("Tầng 2","C24");
        addProduct("Tầng 2","C25 - BM. Quản lý và tin học môi trường");

        addProduct("Tầng 3","C31");
        addProduct("Tầng 3","C32A");
        addProduct("Tầng 3","C32B");
        addProduct("Tầng 3","WC nữ");
        addProduct("Tầng 3","C33");
        addProduct("Tầng 3","C34A - BM. Công nghệ môi trường");
        addProduct("Tầng 3","C34 - BM. Khoa học môi trường");

        addProduct("Tầng 4","C41");
        addProduct("Tầng 4","C42");
        addProduct("Tầng 4","C43A");
        addProduct("Tầng 4","C43B");
        addProduct("Tầng 4","C44 - Trung tâm ngôn ngữ học tính toán");
        addProduct("Tầng 4","C44 - Khoa Công nghệ thông tin\nPhòng nghiên cứu sinh");
    }


    private void loadDataD(){

        addProduct("Tầng 1","PTN. Thực vật\nPhòng học\nPhòng thí nghiệm\nPhòng nghiên cứu");

        addProduct("Tầng 2","PTN. Động vật\nPTN. Sinh học đại cương\nPhòng học\nPhòng thí nghiệm\nPhòng nghiên cứu");
    }


    private void loadDataE(){
        addProduct("Tầng trệt","Phòng khoa học và công nghệ");
        addProduct("Tầng trệt","Phòng y tế");
        addProduct("Tầng trệt","BM. Sinh lý học và CNSH động vật\nPhòng thực tập chuyên ngành sinh lý động vật");
        addProduct("Tầng trệt","PTN. Vi sinh - Sinh môi");

        addProduct("Tầng 1","E101C - Khoa Điện tử viễn thông\nPhòng nghiên cứu khoa học");
        addProduct("Tầng 1","E101A - Khoa Điện tử viễn thông\nPhòng họp");
        addProduct("Tầng 1","E101B - PTN. DESLAB");
        addProduct("Tầng 1","WC nữ\nVC - NLĐ");
        addProduct("Tầng 1","E102 - Khoa Điện tử viễn thông\nPhòng máy tính");
        addProduct("Tầng 1","E103A - BM. Máy tính - Hệ thống nhúng");
        addProduct("Tầng 1","E103B - PTN. Máy tính - Hệ thống nhúng");
        addProduct("Tầng 1","E104 - PTN. Viễn thông");
        addProduct("Tầng 1","PTN. Điện tử");
        addProduct("Tầng 1","BM. Điện tử");
        addProduct("Tầng 1","E106 - BM. Viễn thông và mạng");
        addProduct("Tầng 1","E107 - Văn phòng khoa ĐTVT");

        addProduct("Tầng 2","201B");
        addProduct("Tầng 2","201A - BM. Giải tích");
        addProduct("Tầng 2","WC nam");
        addProduct("Tầng 2","202B - Phòng máy vi tính");
        addProduct("Tầng 2","202A - BM. Ứng dụng tin học");
        addProduct("Tầng 2","203 - BM. Vật lý chất rắn");
        addProduct("Tầng 2","E204");
        addProduct("Tầng 2","E205 - BM. Vật lý tin học\nPTN. Vi điều khiển - Hệ thống nhúng");
        addProduct("Tầng 2","L2.P6");

        addProduct("Tầng 3","E301");
        addProduct("Tầng 3","WC nữ");
        addProduct("Tầng 3","E302");
        addProduct("Tầng 3","E303A - Khoa Vật lý và vật lý kỹ thuật\nBM. Vật lý điện tử");
        addProduct("Tầng 3","E303B - Phòng thực tập điện tử");
        addProduct("Tầng 3","E304 - Khoa Vật lý và vật lý kỹ thuật\nBM. Vật lý tin học");
        addProduct("Tầng 3","BM. Vật lý tin học\nPhòng máy tính");
        addProduct("Tầng 3","E305 - Phòng thiết kế vi mạch và hệ thống nhúng");
        addProduct("Tầng 3","E306 - BM. Vật lý tin học\nPTN chuyên đề");

        addProduct("Tầng 4","E401");
        addProduct("Tầng 4","WC nam");
        addProduct("Tầng 4","E402");
        addProduct("Tầng 4","E403");
        addProduct("Tầng 4","E404 - E405 - E406");
    }


    private void loadDataF(){
        addProduct("Tầng trệt","WC nữ");
        addProduct("Tầng trệt","01 - Phòng tổ chức hành chính");
        addProduct("Tầng trệt","02 - Phòng thông tin - truyền thông");
        addProduct("Tầng trệt","03 - BM. Di truyền\nPTN. Sinh học phân tử");
        addProduct("Tầng trệt","03 - BM. Di truyền\nPTN. Sinh học phân tử");
        addProduct("Tầng trệt","04 - BM. Di truyền\nPTN. Sinh học phân tử");
        addProduct("Tầng trệt","04 - BM. Di truyền\nPTN. Sinh học phân tử");
        addProduct("Tầng trệt","06 - Văn phòng khoa Sinh học - Công nghệ sinh học");
        addProduct("Tầng trệt","07 - Phòng kế hoạch - tài chính");
        addProduct("Tầng trệt","WC CBVC nữ");
        addProduct("Tầng trệt","Tổ giảng đường");
        addProduct("Tầng trệt","08 - Văn phòng khoa Toán - Tin học");
        addProduct("Tầng trệt","09 - Khoa Toán - Tin học");
        addProduct("Tầng trệt","10 - Trung tâm khoa học - toán học");
        addProduct("Tầng trệt","11 - BM. Cơ học");
        addProduct("Tầng trệt","12 - BM. Xác suất thông kê");
        addProduct("Tầng trệt","13 - BM. Tối ưu & Hệ thống");
        addProduct("Tầng trệt","14 - BM. Vật lý ứng dụng\nPTN. Vật lý chân không");
        addProduct("Tầng trệt","15 - PTN. Vật liệu kỹ thuật cao");
        addProduct("Tầng trệt","16 - PTN. Tổng hợp & Phân tích vật liệu màng mỏng");
        addProduct("Tầng trệt","17 - PTN. Vật liệu kỹ thuật cao");
        addProduct("Tầng trệt","WC nam");
        addProduct("Tầng trệt","18 - PTN. Tổng hợp vật liệu màng mỏng");

        addProduct("Tầng 1","WC nam");
        addProduct("Tầng 1","100 - BM. CNSH phân tử - môi trường\nPTN. BM. CNSH phân tử - môi trường");
        addProduct("Tầng 1","101");
        addProduct("Tầng 1","101A - Phòng quan hệ đối ngoại");
        addProduct("Tầng 1","101B - PTN. CNSH phân tử B");
        addProduct("Tầng 1","F102 - Phòng họp");
        addProduct("Tầng 1","F.102 - Phòng họp");
        addProduct("Tầng 1","Rest Room");
        addProduct("Tầng 1","103 - Văn phòng hội đồng trường");
        addProduct("Tầng 1","104");
        addProduct("Tầng 1","F.105 - Phòng quan hệ đối ngoại");
        addProduct("Tầng 1","F.106A - Văn phòng ban chỉ huy quân sự\nVăn phòng Đảng ủy\nVăn phòng hội cựu chiến binh");
        addProduct("Tầng 1","F.106B - Văn phòng công đoàn");
        addProduct("Tầng 1","F.107 - Văn phòng tiếp công dân\nPhòng thanh tra pháp chế");
        addProduct("Tầng 1","F.108 - Văn phòng đoàn thanh niên\nVăn phòng hội sinh viên");
        addProduct("Tầng 1","109 - Phóng máy\nKhoa Công nghệ thông tin");
        addProduct("Tầng 1","110 - Phóng máy\nKhoa Công nghệ thông tin");
        addProduct("Tầng 1","111");
        addProduct("Tầng 1","F.112A - Khoa Khoa học và công nghệ vật liệu");
        addProduct("Tầng 1","112 - BM. Sinh lý học và CNSH động vật");
        addProduct("Tầng 1","F.113 - Văn phòng khoa Khoa học và công nghệ vật liệu");

        addProduct("Tầng 2","Khoa môi trường - Phòng đọc");
        addProduct("Tầng 2","200 - Khoa môi trường\nPTN. Tin học môi trường");
        addProduct("Tầng 2","202");
        addProduct("Tầng 2","203");
        addProduct("Tầng 2","University council - Office of the chair");
        addProduct("Tầng 2","F205a");
        addProduct("Tầng 2","F205b");
        addProduct("Tầng 2","F.206A - BM. Tài chính định lượng");
        addProduct("Tầng 2","206 - BM. Giáo dục toán học");
        addProduct("Tầng 2","207");
        addProduct("Tầng 2","208 - Phòng máy\nKhoa Toán - Tin học");
        addProduct("Tầng 2","209 - Phòng máy\nKhoa Toán - Tin học");
        addProduct("Tầng 2","F.210 - BM. Đại số");
        addProduct("Tầng 2","F.211 - PTN. Tổng hợp vật liệu polyme");

        addProduct("Tầng 3","300");
        addProduct("Tầng 3","301");
        addProduct("Tầng 3","302");
        addProduct("Tầng 3","303");
        addProduct("Tầng 3","304");
        addProduct("Tầng 3","305");
        addProduct("Tầng 3","BM. Vật liệu màng mỏng\nBM. Vật liệu từ & Y sinh");
        addProduct("Tầng 3","305 - PTN. Phân tích vật liệu");

        addProduct("Tầng 4","400 - PTN. Phân tích vật liệu polyme");
    }


    private void loadDataH(){
        addProduct("Tầng trệt","H2.1");
        addProduct("Tầng trệt","H2.2");

        addProduct("Tầng 1","H2.3");
    }


    private void loadDataI(){
        addProduct("Tầng 1","I.11 - Phòng họp");
        addProduct("Tầng 1","I.12 - Phòng họp");
        addProduct("Tầng 1","WC nam nữ");
        addProduct("Tầng 1","Hội trường I");

        addProduct("Tầng 2","23");
        addProduct("Tầng 2","I.24");
        addProduct("Tầng 2","WC nam nữ");
        addProduct("Tầng 2","25 - BM. Hóa vô cơ và ứng dụng\nPhòng nghiên cứu");
        addProduct("Tầng 2","26");
        addProduct("Tầng 2","27 - BM. Hóa vô cơ và ứng dụng\nPhòng nghiên cứu");

        addProduct("Tầng 3","35");
        addProduct("Tầng 3","34");
        addProduct("Tầng 3","33");
        addProduct("Tầng 3","32");
        addProduct("Tầng 3","31");
        addProduct("Tầng 3","WC nam nữ");
        addProduct("Tầng 3","35 - PTN. Hóa vô cơ chuyên ngành");
        addProduct("Tầng 3","36 - BM. Hóa vô cơ và ứng dụng");
        addProduct("Tầng 3","37 - Văn phòng bộ môn Hóa vô cơ và ứng dụng");
        addProduct("Tầng 3","38 - Phòng hội thảo");

        addProduct("Tầng 4","I41 - APCS-401");
        addProduct("Tầng 4","I42 - APCS-402");
        addProduct("Tầng 4","I43 - Văn phòng các chương trình đào tạo đặc biệt ngành CNTT");
        addProduct("Tầng 4","I44 - APCS-404");
        addProduct("Tầng 4","WC nam nữ");
        addProduct("Tầng 4","46 - PTN. Hóa dược");
        addProduct("Tầng 4","47 - BM. Hóa phân tích\nPTN. Hóa phân tích chuyên ngành");
        addProduct("Tầng 4","48 - BM. Hóa phân tích");

        addProduct("Tầng 5","51 - Bộ phận kỹ thuật");
        addProduct("Tầng 5","52 - Phòng máy tính");
        addProduct("Tầng 5","53 - Bộ phận giáo vụ\nTrợ lý sinh viên");
        addProduct("Tầng 5","54 - Văn phòng khoa Công nghệ thông tin");
        addProduct("Tầng 5","55 - Phòng họp");
        addProduct("Tầng 5","WC nam nữ");
        addProduct("Tầng 5","56 - PTN. Hóa lý ứng dụng");
        addProduct("Tầng 5","57 - BM. Hóa lý\nPTN. Hóa nano");
        addProduct("Tầng 5","58 - BM. Hóa lý\nPTN. Hóa lý hữu cơ");
        addProduct("Tầng 5","59 - BM. Hóa dược");
        addProduct("Tầng 5","59A - Phòng hội thảo");
        addProduct("Tầng 5","59B - Văn phòng khoa");

        addProduct("Tầng 6","61 - Phòng máy tính");
        addProduct("Tầng 6","62 - Phòng máy tính");
        addProduct("Tầng 6","63 - BM. Tin học cơ sở\nBM. Công nghệ tri thức");
        addProduct("Tầng 6","64 - PTN. Trí tuệ nhân tạo");
        addProduct("Tầng 6","WC nam nữ");
        addProduct("Tầng 6","65 - BM. Hóa học cao phân tử\nPTN. Polyme 1");
        addProduct("Tầng 6","66 - BM. Hóa lý\nPTN. Hóa lý thuyết");
        addProduct("Tầng 6","67 - BM. Hóa lý\nPTN. Hóa lý thuyết");
        addProduct("Tầng 6","68 - BM. Hóa lý\nPTN. Điện hóa");
        addProduct("Tầng 6","69 - BM. Hóa học cao phân tử\nPTN. Polyme 2");
        addProduct("Tầng 6","69A - Văn phòng bộ môn");

        addProduct("Tầng 7","71 - Phòng máy tính");
        addProduct("Tầng 7","72A - Phòng họp");
        addProduct("Tầng 7","72 - BM. Thị giác máy tính và khoa học robot");
        addProduct("Tầng 7","73 - PTN. An ninh mạng");
        addProduct("Tầng 7","74 - BM. Mạng máy tính và viễn thông");
        addProduct("Tầng 7","75");
        addProduct("Tầng 7","WC nam nữ");
        addProduct("Tầng 7","76 - Phòng họp sinh viên");
        addProduct("Tầng 7","77 - PTN. Hóa tin");
        addProduct("Tầng 7","78 - PTN. Hóa tin");
        addProduct("Tầng 7","79");
        addProduct("Tầng 7","79A");

        addProduct("Tầng 8","81 - BM. Khoa học máy tính");
        addProduct("Tầng 8","82 - BM. Công nghệ phần mềm");
        addProduct("Tầng 8","84 - BM. Hệ thống thông tin");
        addProduct("Tầng 8","85 - Phòng thông tin - truyền thông");
        addProduct("Tầng 8","WC nam nữ");
        addProduct("Tầng 8","86 - PTN. Hệ thống nhúng");
        addProduct("Tầng 8","87 - PTN. Công nghệ phần mềm");
        addProduct("Tầng 8","87A - PTN. Hệ thống di động & media");
        addProduct("Tầng 8","88");
        addProduct("Tầng 8","89 - Phòng vật lý tính toán");

        addProduct("Tầng 9","Thư viện");
        addProduct("Tầng 9","91");
        addProduct("Tầng 9","92");

        addProduct("Tầng 10","Thư viện");
        addProduct("Tầng 10","Phòng luận văn");

        addProduct("Tầng 11","11A - Phòng học");
        addProduct("Tầng 11","11B - Phòng học");
        addProduct("Tầng 11","11C - Phòng học");
        addProduct("Tầng 11","Phòng thông tin - truyền thông");
        addProduct("Tầng 11","WC nam nữ");
        addProduct("Tầng 11","11E - Phòng máy");
        addProduct("Tầng 11","11F - Phòng máy");
        addProduct("Tầng 11","11G2 - Phòng máy");
        addProduct("Tầng 11","11G1 - Phòng máy");
        addProduct("Tầng 11","ITEC office");

        addProduct("Tầng 12","I.12A");
        addProduct("Tầng 12","I.12B - Khoa Điện tử viễn thông\nPTN. CLC 2");
        addProduct("Tầng 12","I.12C");
    }
}