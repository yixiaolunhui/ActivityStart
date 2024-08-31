package com.zwl.activitystart.chain

interface TaskChain {
    fun addTask(task: TaskInterceptor)
    fun addTaskAt(index: Int, task: TaskInterceptor)
    fun proceed()
}

interface TaskInterceptor {
    fun execute(chain: TaskChain)
}

interface TaskChainStatusListener {
    fun onStatusChanged(isEndOfChain: Boolean)
}

class TaskChainManager : TaskChain {

    private val tasks = mutableListOf<TaskInterceptor>()
    private var currentIndex = 0
    private var statusListener: TaskChainStatusListener? = null

    fun setStatusListener(listener: TaskChainStatusListener) {
        this.statusListener = listener
    }

    override fun addTask(task: TaskInterceptor) {
        tasks.add(task)
    }

    override fun addTaskAt(index: Int, task: TaskInterceptor) {
        if (index in 0..tasks.size) {
            tasks.add(index, task)
        } else {
            throw IndexOutOfBoundsException("Index $index out of bounds for task size ${tasks.size}")
        }
    }

    fun getTaskCount() = tasks.size

    override fun proceed() {
        if (currentIndex >= tasks.size) {
            statusListener?.onStatusChanged(true)
        } else {
            statusListener?.onStatusChanged(false)
            tasks[currentIndex++].execute(this)
        }
    }

    fun reset() {
        tasks.clear()
        currentIndex = 0
    }
}

object TaskChainBuilder {

    fun builder() = Builder()

    class Builder {
        private val taskChainManager = TaskChainManager()

        fun addTask(task: TaskInterceptor) = apply {
            taskChainManager.addTask(task)
        }

        fun addTaskAt(index: Int, task: TaskInterceptor) = apply {
            taskChainManager.addTaskAt(index, task)
        }

        fun setStatusListener(listener: TaskChainStatusListener) = apply {
            taskChainManager.setStatusListener(listener)
        }

        fun execute() {
            if (taskChainManager.getTaskCount() > 0) {
                taskChainManager.proceed()
            }
        }

        val manager: TaskChainManager
            get() = taskChainManager
    }
}
