import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
class RunServiceOnBoot : Service() {
    private val tag = "CahrgeCalc"
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val runTime = 5000
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        Log.i(tag, "onCreate")
        handler = Handler()
        runnable = Runnable { handler.postDelayed(runnable, runTime.toLong()) }
        handler.post(runnable)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.i(tag, "onStart")
    }
}