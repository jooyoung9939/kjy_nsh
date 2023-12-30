package com.example.madcamp_week1_tab1_try

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_week1_tab1_try.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //private lateinit var binding: ActivityMainBinding
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//      setContentView(R.layout.fragment_a)

        //1.페이지 데이터를 업로드
        val list = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD())
        //2. 아답터를 생성
        val pagerAdapter = FragmentPagerAdapter(list, this)
        //3. 아답터와 뷰페이저 연결
        binding.viewPager.adapter = pagerAdapter

        //4. 탭 메뉴의 개수만큼 제목을 목록으로 생성
        val titles = listOf("A", "B", "C", "D")

        //5. 탬레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            tab.text = titles.get(position)
        }.attach()
    }
class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList.get(position)

}
}


