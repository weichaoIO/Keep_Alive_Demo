package io.weichao.keep_alive_demo.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobHandlerService extends JobService {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("服务被创建" + getTime());

        JobInfo.Builder builder = new JobInfo.Builder(startId++, new ComponentName(getPackageName(), JobHandlerService.class.getName()));
        builder.setPeriodic(5000); //每隔5秒运行一次
        builder.setRequiresCharging(true);
        builder.setPersisted(true);  //设置设备重启后，是否重新执行任务
        builder.setRequiresDeviceIdle(true);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler.schedule(builder.build()) <= 0) {
            System.out.println("工作失败" + getTime());
        } else {
            System.out.println("工作成功" + getTime());
        }

        return START_STICKY;
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "服务启动" + getTime(), Toast.LENGTH_SHORT).show();
        System.out.println("开始工作" + getTime());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    private String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date data = new Date(System.currentTimeMillis());//获取当前时间
        return "---" + formatter.format(data);
    }
}