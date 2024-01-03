package com.example.madcamp_week1_tab1_try

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_week1_tab1_try.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(){
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var currentFragment: Fragment? = null
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // ViewModelProvider를 사용하여 ViewModel 인스턴스 생성
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        // 페이지 데이터 로드
        val list = listOf(FragmentD(), FragmentC(), FragmentB())

        // 아답터 생성 및 연결
        val pagerAdapter = FragmentPagerAdapter(list, this)
        binding.viewPager.adapter = pagerAdapter

        // 탭 메뉴 제목 설정
        val titles = listOf("주소록", "갤러리", "추가")

        // 탭 레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        // 현재 활성화된 프래그먼트 추적
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentFragment = list[position]
            }
        })
    }
    class FragmentPagerAdapter(
        private val fragmentList: List<Fragment>,
        fragmentActivity: AppCompatActivity
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}
