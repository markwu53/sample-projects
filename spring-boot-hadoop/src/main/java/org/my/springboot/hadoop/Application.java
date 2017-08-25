package org.my.springboot.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.hadoop.fs.FsShell;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired ApplicationContext context;
    @Autowired FsShell shell;
    @Autowired Configuration hadoop;
 
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        /*
        Arrays.asList(context.getBeanDefinitionNames()).stream()
            .filter(s -> !s.startsWith("org.") && !s.startsWith("spring."))
            .map(s -> new String[] { s, context.getBean(s).getClass().getName() })
            .forEach(ss -> {
                System.out.println(String.format("%s: %s", ss[0], ss[1]));
            });
            //.collect(Collectors.toList());
        shell.ls("/user").stream()
            .map(s -> s.getPath().getName())
            .forEach(System.out::println);
        hadoop.forEach(e -> {
            System.out.println(String.format("%s=%s", e.getKey(), e.getValue()));
        });
        */
        shell.ls("/user").stream()
            .map(FileStatus::getPath)
            .map(Path::getName)
            .filter(s -> s.startsWith("T"))
            .map(s -> s.substring(1))
            .map(Integer::valueOf)
            .forEach(System.out::println);
    }

    /*
mapreduce.jobtracker.address=local
yarn.resourcemanager.scheduler.monitor.policies=org.apache.hadoop.yarn.server.resourcemanager.monitor.capacity.ProportionalCapacityPreemptionPolicy
dfs.namenode.resource.check.interval=5000
yarn.resourcemanager.leveldb-state-store.compaction-interval-secs=3600
mapreduce.jobhistory.client.thread-count=10
yarn.app.mapreduce.am.containerlauncher.threadpool-initial-size=10
mapred.child.java.opts=-Xmx200m
mapreduce.jobtracker.retiredjobs.cache.size=1000
dfs.client.https.need-auth=false
yarn.admin.acl=*
yarn.app.mapreduce.am.job.committer.cancel-timeout=60000
mapreduce.job.emit-timeline-data=false
fs.ftp.host.port=21
dfs.namenode.avoid.read.stale.datanode=false
dfs.journalnode.rpc-address=0.0.0.0:8485
mapreduce.job.end-notification.retry.attempts=0
yarn.timeline-service.leveldb-timeline-store.ttl-interval-ms=300000
yarn.resourcemanager.leveldb-state-store.path=${hadoop.tmp.dir}/yarn/system/rmstore
yarn.ipc.rpc.class=org.apache.hadoop.yarn.ipc.HadoopYarnProtoRPC
ipc.client.connection.maxidletime=10000
yarn.nodemanager.process-kill-wait.ms=2000
mapreduce.cluster.acls.enabled=false
mapreduce.jobtracker.handler.count=10
io.map.index.interval=128
dfs.namenode.https-address=0.0.0.0:50470
mapreduce.task.profile.reduces=0-2
fs.s3n.multipart.uploads.enabled=false
io.seqfile.sorter.recordlimit=1000000
mapreduce.job.ubertask.maxmaps=9
mapreduce.tasktracker.tasks.sleeptimebeforesigkill=5000
hadoop.util.hash.type=murmur
dfs.namenode.replication.min=1
yarn.nodemanager.container-manager.thread-count=20
mapreduce.jobtracker.jobhistory.block.size=3145728
dfs.namenode.fs-limits.min-block-size=1048576
mapreduce.app-submission.cross-platform=false
fs.AbstractFileSystem.file.impl=org.apache.hadoop.fs.local.LocalFs
net.topology.script.number.args=100
yarn.resourcemanager.container-tokens.master-key-rolling-interval-secs=86400
mapreduce.map.output.compress.codec=org.apache.hadoop.io.compress.DefaultCodec
mapreduce.job.reducer.preempt.delay.sec=0
s3native.bytes-per-checksum=512
dfs.namenode.path.based.cache.block.map.allocation.percent=0.25
yarn.nodemanager.windows-container.memory-limit.enabled=false
mapreduce.input.fileinputformat.split.minsize=0
hadoop.security.group.mapping=org.apache.hadoop.security.JniBasedUnixGroupsMappingWithFallback
mapreduce.jobtracker.system.dir=${hadoop.tmp.dir}/mapred/system
mapreduce.job.end-notification.max.attempts=5
mapreduce.reduce.markreset.buffer.percent=0.0
dfs.datanode.cache.revocation.polling.ms=500
mapreduce.reduce.speculative=true
yarn.nodemanager.localizer.cache.cleanup.interval-ms=600000
dfs.namenode.lazypersist.file.scrub.interval.sec=300
fs.s3a.threads.core=15
mapreduce.jobhistory.recovery.store.fs.uri=${hadoop.tmp.dir}/mapred/history/recoverystore
yarn.timeline-service.leveldb-timeline-store.start-time-read-cache-size=10000
hadoop.registry.zk.retry.interval.ms=1000
yarn.nodemanager.keytab=/etc/krb5.keytab
dfs.namenode.replication.interval=3
yarn.resourcemanager.admin.address=${yarn.resourcemanager.hostname}:8033
mapreduce.job.maps=2
nfs.dump.dir=/tmp/.hdfs-nfs
mapreduce.jobtracker.maxtasks.perjob=-1
mapreduce.job.ubertask.enable=false
yarn.nodemanager.delete.debug-delay-sec=0
yarn.timeline-service.ttl-enable=true
yarn.resourcemanager.fs.state-store.retry-policy-spec=2000, 500
mapreduce.reduce.skip.maxgroups=0
dfs.client.use.datanode.hostname=false
fs.trash.interval=0
mapreduce.am.max-attempts=2
mapreduce.jobtracker.heartbeats.in.second=100
yarn.resourcemanager.zk-num-retries=1000
s3.blocksize=67108864
dfs.datanode.data.dir=file://${hadoop.tmp.dir}/dfs/data
mapreduce.jobtracker.persist.jobstatus.active=true
mapreduce.reduce.shuffle.parallelcopies=5
fs.s3.buffer.dir=${hadoop.tmp.dir}/s3
mapreduce.jobhistory.done-dir=${yarn.app.mapreduce.am.staging-dir}/history/done
hadoop.security.instrumentation.requires.admin=false
hadoop.registry.zk.retry.ceiling.ms=60000
nfs.rtmax=1048576
dfs.datanode.data.dir.perm=700
yarn.resourcemanager.container.liveness-monitor.interval-ms=600000
yarn.nodemanager.env-whitelist=JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,HADOOP_YARN_HOME
dfs.namenode.backup.address=0.0.0.0:50100
dfs.namenode.xattrs.enabled=true
dfs.datanode.readahead.bytes=4194304
dfs.datanode.bp-ready.timeout=20
yarn.app.mapreduce.client.job.max-retries=0
mapreduce.jobhistory.cleaner.enable=true
dfs.client.block.write.retries=3
mapreduce.tasktracker.http.address=0.0.0.0:50060
yarn.nodemanager.linux-container-executor.cgroups.hierarchy=/hadoop-yarn
ha.failover-controller.graceful-fence.connection.retries=1
yarn.resourcemanager.recovery.enabled=false
yarn.app.mapreduce.am.container.log.backups=0
dfs.namenode.safemode.threshold-pct=0.999f
yarn.nodemanager.disk-health-checker.interval-ms=120000
hadoop.security.java.secure.random.algorithm=SHA1PRNG
dfs.namenode.list.cache.directives.num.responses=100
dfs.datanode.dns.nameserver=default
mapreduce.cluster.temp.dir=${hadoop.tmp.dir}/mapred/temp
fs.s3a.max.total.tasks=1000
mapreduce.reduce.maxattempts=4
mapreduce.client.submit.file.replication=10
mapreduce.shuffle.port=13562
yarn.fail-fast=false
yarn.resourcemanager.resource-tracker.client.thread-count=50
dfs.namenode.replication.considerLoad=true
yarn.resourcemanager.webapp.cross-origin.enabled=false
dfs.namenode.edits.journal-plugin.qjournal=org.apache.hadoop.hdfs.qjournal.client.QuorumJournalManager
dfs.client.write.exclude.nodes.cache.expiry.interval.millis=600000
yarn.nodemanager.delete.thread-count=4
dfs.client.mmap.cache.timeout.ms=3600000
yarn.nodemanager.admin-env=MALLOC_ARENA_MAX=$MALLOC_ARENA_MAX
io.skip.checksum.errors=false
yarn.timeline-service.hostname=0.0.0.0
yarn.resourcemanager.proxy-user-privileges.enabled=false
mapreduce.job.speculative.speculative-cap-total-tasks=0.01
yarn.acl.enable=false
fs.s3a.fast.upload=false
file.blocksize=67108864
mapreduce.job.speculative.slowtaskthreshold=1.0
ftp.replication=3
yarn.sharedcache.cleaner.initial-delay-mins=10
s3native.client-write-packet-size=65536
hadoop.rpc.socket.factory.class.default=org.apache.hadoop.net.StandardSocketFactory
file.bytes-per-checksum=512
dfs.datanode.slow.io.warning.threshold.ms=300
rpc.engine.org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB=org.apache.hadoop.ipc.ProtobufRpcEngine
io.seqfile.lazydecompress=true
mapreduce.task.skip.start.attempts=2
dfs.namenode.reject-unresolved-dn-topology-mapping=false
hadoop.common.configuration.version=0.23.0
yarn.resourcemanager.client.thread-count=50
dfs.datanode.drop.cache.behind.reads=false
yarn.sharedcache.admin.address=0.0.0.0:8047
mapreduce.jobtracker.taskcache.levels=2
yarn.nodemanager.linux-container-executor.nonsecure-mode.user-pattern=^[_.A-Za-z0-9][-@_.A-Za-z0-9]{0,255}?[$]?$
yarn.resourcemanager.zk-timeout-ms=10000
yarn.resourcemanager.max-completed-applications=10000
mapreduce.job.jvm.numtasks=1
yarn.sharedcache.cleaner.period-mins=1440
mapreduce.jobtracker.tasktracker.maxblacklists=4
dfs.namenode.top.num.users=10
yarn.nodemanager.linux-container-executor.cgroups.mount=false
yarn.sharedcache.checksum.algo.impl=org.apache.hadoop.yarn.sharedcache.ChecksumSHA256Impl
mapreduce.job.end-notification.max.retry.interval=5000
mapreduce.job.acl-view-job= 
mapreduce.job.classloader=false
yarn.log-aggregation-enable=false
mapreduce.reduce.shuffle.fetch.retry.interval-ms=1000
yarn.resourcemanager.nodemanager.minimum.version=NONE
hadoop.security.kms.client.encrypted.key.cache.size=500
yarn.app.mapreduce.am.job.task.listener.thread-count=30
yarn.app.mapreduce.am.resource.cpu-vcores=1
dfs.namenode.edit.log.autoroll.check.interval.ms=300000
mapreduce.output.fileoutputformat.compress.type=RECORD
hadoop.hdfs.configuration.version=1
hadoop.security.group.mapping.ldap.search.attr.member=member
yarn.nodemanager.log.retain-seconds=10800
yarn.nodemanager.local-cache.max-files-per-directory=8192
mapreduce.job.end-notification.retry.interval=1000
hadoop.ssl.client.conf=ssl-client.xml
yarn.sharedcache.root-dir=/sharedcache
dfs.journalnode.https-address=0.0.0.0:8481
mapreduce.reduce.shuffle.fetch.retry.timeout-ms=30000
dfs.bytes-per-checksum=512
dfs.namenode.max.objects=0
mapreduce.tasktracker.instrumentation=org.apache.hadoop.mapred.TaskTrackerMetricsInst
dfs.datanode.max.transfer.threads=4096
dfs.block.access.key.update.interval=600
mapreduce.jobtracker.jobhistory.task.numberprogresssplits=12
mapreduce.map.memory.mb=1024
ha.failover-controller.new-active.rpc-timeout.ms=60000
hadoop.ssl.hostname.verifier=DEFAULT
dfs.client.read.shortcircuit=false
dfs.datanode.hdfs-blocks-metadata.enabled=false
mapreduce.tasktracker.healthchecker.interval=60000
dfs.image.transfer.chunksize=65536
mapreduce.tasktracker.taskmemorymanager.monitoringinterval=5000
dfs.client.https.keystore.resource=ssl-client.xml
yarn.resourcemanager.connect.retry-interval.ms=30000
yarn.timeline-service.webapp.address=${yarn.timeline-service.hostname}:8188
s3native.blocksize=67108864
yarn.scheduler.minimum-allocation-mb=1024
yarn.sharedcache.cleaner.resource-sleep-ms=0
net.topology.impl=org.apache.hadoop.net.NetworkTopology
dfs.client.failover.sleep.base.millis=500
dfs.permissions.superusergroup=supergroup
io.seqfile.compress.blocksize=1000000
hadoop.registry.zk.retry.times=5
fs.AbstractFileSystem.ftp.impl=org.apache.hadoop.fs.ftp.FtpFs
dfs.namenode.checkpoint.edits.dir=${dfs.namenode.checkpoint.dir}
mapreduce.job.running.reduce.limit=0
dfs.blockreport.initialDelay=0
dfs.namenode.heartbeat.recheck-interval=300000
dfs.namenode.safemode.extension=30000
yarn.scheduler.maximum-allocation-mb=8192
mapreduce.job.reduce.shuffle.consumer.plugin.class=org.apache.hadoop.mapreduce.task.reduce.Shuffle
yarn.nodemanager.vmem-check-enabled=true
mapreduce.task.io.sort.factor=10
mapreduce.jobtracker.persist.jobstatus.dir=/jobtracker/jobsInfo
dfs.client.failover.sleep.max.millis=15000
dfs.namenode.delegation.key.update-interval=86400000
hadoop.rpc.protection=authentication
fs.permissions.umask-mode=022
fs.s3.sleepTimeSeconds=10
ha.health-monitor.rpc-timeout.ms=45000
hadoop.http.staticuser.user=dr.who
fs.s3a.connection.maximum=15
yarn.nodemanager.linux-container-executor.nonsecure-mode.limit-users=true
fs.s3a.paging.maximum=5000
fs.AbstractFileSystem.viewfs.impl=org.apache.hadoop.fs.viewfs.ViewFs
fs.ftp.host=0.0.0.0
yarn.nodemanager.linux-container-executor.nonsecure-mode.local-user=nobody
fs.s3a.impl=org.apache.hadoop.fs.s3a.S3AFileSystem
hadoop.http.authentication.kerberos.keytab=${user.home}/hadoop.keytab
dfs.namenode.fs-limits.max-blocks-per-file=1048576
mapreduce.tasktracker.http.threads=40
mapreduce.tasktracker.dns.nameserver=default
yarn.resourcemanager.am-rm-tokens.master-key-rolling-interval-secs=86400
dfs.client.block.write.replace-datanode-on-failure.enable=true
io.compression.codec.bzip2.library=system-native
mapreduce.map.skip.maxrecords=0
dfs.client.use.legacy.blockreader.local=false
dfs.namenode.checkpoint.dir=file://${hadoop.tmp.dir}/dfs/namesecondary
dfs.namenode.top.windows.minutes=1,5,25
ipc.ping.interval=60000
mapreduce.job.maxtaskfailures.per.tracker=3
net.topology.node.switch.mapping.impl=org.apache.hadoop.net.ScriptBasedMapping
mapreduce.shuffle.max.connections=0
mapreduce.jobhistory.loadedjobs.cache.size=5
dfs.storage.policy.enabled=true
yarn.client.application-client-protocol.poll-interval-ms=200
yarn.nodemanager.localizer.address=${yarn.nodemanager.hostname}:8040
dfs.namenode.list.cache.pools.num.responses=100
nfs.server.port=2049
mapreduce.client.output.filter=FAILED
yarn.timeline-service.client.best-effort=false
ha.zookeeper.parent-znode=/hadoop-ha
mapreduce.jobtracker.persist.jobstatus.hours=1
yarn.sharedcache.admin.thread-count=1
yarn.nodemanager.resource.cpu-vcores=8
dfs.datanode.block-pinning.enabled=false
mapreduce.jobhistory.http.policy=HTTP_ONLY
yarn.resourcemanager.webapp.delegation-token-auth-filter.enabled=true
mapreduce.job.speculative.retry-after-no-speculate=1000
fs.s3a.attempts.maximum=10
s3native.stream-buffer-size=4096
io.seqfile.local.dir=${hadoop.tmp.dir}/io/local
yarn.log-aggregation.retain-check-interval-seconds=-1
fs.s3n.multipart.copy.block.size=5368709120
dfs.encrypt.data.transfer.cipher.key.bitlength=128
dfs.datanode.sync.behind.writes=false
yarn.resourcemanager.zk-acl=world:anyone:rwcda
hadoop.ssl.keystores.factory.class=org.apache.hadoop.security.ssl.FileBasedKeyStoresFactory
mapreduce.job.split.metainfo.maxsize=10000000
fs.s3.maxRetries=4
hadoop.security.random.device.file.path=/dev/urandom
yarn.client.nodemanager-connect.max-wait-ms=180000
dfs.namenode.stale.datanode.interval=30000
mapreduce.task.io.sort.mb=100
yarn.resourcemanager.zk-state-store.parent-path=/rmstore
yarn.app.mapreduce.client-am.ipc.max-retries=3
fs.client.resolve.remote.symlinks=true
yarn.nodemanager.linux-container-executor.cgroups.strict-resource-usage=false
hadoop.ssl.enabled.protocols=TLSv1
mapreduce.reduce.cpu.vcores=1
yarn.client.failover-retries=0
mapreduce.jobhistory.address=0.0.0.0:10020
hadoop.ssl.enabled=false
dfs.replication.max=512
dfs.namenode.name.dir=file://${hadoop.tmp.dir}/dfs/name
dfs.datanode.https.address=0.0.0.0:50475
ipc.client.kill.max=10
mapreduce.job.committer.setup.cleanup.needed=true
dfs.client.domain.socket.data.traffic=false
yarn.nodemanager.localizer.cache.target-size-mb=10240
yarn.resourcemanager.admin.client.thread-count=1
dfs.block.access.token.enable=false
mapreduce.job.speculative.retry-after-speculate=15000
dfs.datanode.address=0.0.0.0:50010
mapreduce.jobtracker.restart.recover=false
ipc.client.connect.max.retries=10
yarn.timeline-service.store-class=org.apache.hadoop.yarn.server.timeline.LeveldbTimelineStore
dfs.short.circuit.shared.memory.watcher.interrupt.check.ms=60000
hadoop.tmp.dir=/tmp/hadoop-${user.name}
dfs.datanode.handler.count=10
yarn.resourcemanager.ha.automatic-failover.embedded=true
yarn.timeline-service.ttl-ms=604800000
mapreduce.task.profile.map.params=${mapreduce.task.profile.params}
yarn.resourcemanager.nodemanagers.heartbeat-interval-ms=1000
mapreduce.map.speculative=true
dfs.namenode.block-placement-policy.default.prefer-local-node=true
yarn.timeline-service.recovery.enabled=false
dfs.namenode.resource.checked.volumes.minimum=1
yarn.nodemanager.recovery.dir=${hadoop.tmp.dir}/yarn-nm-recovery
mapreduce.job.counters.max=120
yarn.resourcemanager.keytab=/etc/krb5.keytab
dfs.namenode.max.extra.edits.segments.retained=10000
dfs.webhdfs.user.provider.user.pattern=^[A-Za-z_][A-Za-z0-9._-]*[$]?$
dfs.client.mmap.enabled=true
mapreduce.map.log.level=INFO
dfs.client.file-block-storage-locations.timeout.millis=1000
yarn.client.max-cached-nodemanagers-proxies=0
yarn.app.mapreduce.am.scheduler.heartbeat.interval-ms=1000
hadoop.fuse.timer.period=5
fs.trash.checkpoint.interval=0
yarn.sharedcache.app-checker.class=org.apache.hadoop.yarn.server.sharedcachemanager.RemoteAppChecker
dfs.journalnode.http-address=0.0.0.0:8480
yarn.app.mapreduce.am.staging-dir=/tmp/hadoop-yarn/staging
mapreduce.tasktracker.local.dir.minspacestart=0
yarn.nm.liveness-monitor.expiry-interval-ms=600000
ha.health-monitor.check-interval.ms=1000
mapreduce.reduce.shuffle.merge.percent=0.66
dfs.namenode.retrycache.heap.percent=0.03f
yarn.nodemanager.docker-container-executor.exec-name=/usr/bin/docker
ipc.client.connect.timeout=20000
yarn.resourcemanager.fs.state-store.retry-interval-ms=1000
mapreduce.output.fileoutputformat.compress=false
yarn.nodemanager.local-dirs=${hadoop.tmp.dir}/nm-local-dir
yarn.nodemanager.recovery.enabled=false
io.native.lib.available=true
yarn.sharedcache.store.in-memory.staleness-period-mins=10080
yarn.resourcemanager.am.max-attempts=2
s3.replication=3
fs.AbstractFileSystem.har.impl=org.apache.hadoop.fs.HarFs
dfs.image.compress=false
mapreduce.job.running.map.limit=0
mapreduce.reduce.input.buffer.percent=0.0
yarn.nodemanager.webapp.address=${yarn.nodemanager.hostname}:8042
dfs.datanode.available-space-volume-choosing-policy.balanced-space-preference-fraction=0.75f
dfs.namenode.edit.log.autoroll.multiplier.threshold=2.0
hadoop.security.group.mapping.ldap.ssl=false
dfs.namenode.checkpoint.check.period=60
fs.defaultFS=hdfs://192.168.121.129:8020
fs.s3a.multipart.size=104857600
dfs.client.slow.io.warning.threshold.ms=30000
yarn.app.mapreduce.am.job.committer.commit-window=10000
hadoop.security.group.mapping.ldap.search.attr.group.name=cn
yarn.nodemanager.disk-health-checker.max-disk-utilization-per-disk-percentage=90.0
yarn.nodemanager.container-monitor.procfs-tree.smaps-based-rss.enabled=false
mapreduce.map.sort.spill.percent=0.80
hadoop.security.crypto.codec.classes.aes.ctr.nopadding=org.apache.hadoop.crypto.OpensslAesCtrCryptoCodec,org.apache.hadoop.crypto.JceAesCtrCryptoCodec
dfs.namenode.http-address=0.0.0.0:50070
hadoop.security.groups.negative-cache.secs=30
hadoop.ssl.server.conf=ssl-server.xml
mapreduce.ifile.readahead=true
s3native.replication=3
yarn.client.nodemanager-client-async.thread-pool-max-size=500
mapreduce.jobtracker.staging.root.dir=${hadoop.tmp.dir}/mapred/staging
mapreduce.jobhistory.admin.address=0.0.0.0:10033
dfs.namenode.startup.delay.block.deletion.sec=0
yarn.nodemanager.health-checker.interval-ms=600000
dfs.namenode.checkpoint.max-retries=3
s3.stream-buffer-size=4096
ftp.client-write-packet-size=65536
dfs.datanode.fsdatasetcache.max.threads.per.volume=4
mapreduce.output.fileoutputformat.compress.codec=org.apache.hadoop.io.compress.DefaultCodec
yarn.timeline-service.keytab=/etc/krb5.keytab
yarn.sharedcache.store.in-memory.initial-delay-mins=10
mapreduce.jobhistory.webapp.address=0.0.0.0:19888
mapreduce.task.userlog.limit.kb=0
mapreduce.reduce.shuffle.fetch.retry.enabled=${yarn.nodemanager.recovery.enabled}
yarn.app.mapreduce.task.container.log.backups=0
dfs.heartbeat.interval=3
ha.zookeeper.session-timeout.ms=5000
fs.s3a.connection.ssl.enabled=true
yarn.sharedcache.webapp.address=0.0.0.0:8788
hadoop.http.authentication.signature.secret.file=${user.home}/hadoop-http-auth-signature-secret
hadoop.fuse.connection.timeout=300
yarn.nodemanager.log-aggregation.compression-type=none
ipc.server.max.connections=0
yarn.resourcemanager.work-preserving-recovery.scheduling-wait-ms=10000
yarn.nodemanager.log-dirs=${yarn.log.dir}/userlogs
yarn.app.mapreduce.am.resource.mb=1536
hadoop.security.groups.cache.secs=300
mapreduce.job.speculative.minimum-allowed-tasks=10
yarn.nodemanager.container-monitor.interval-ms=3000
dfs.datanode.cache.revocation.timeout.ms=900000
mapreduce.jobhistory.recovery.store.class=org.apache.hadoop.mapreduce.v2.hs.HistoryServerFileSystemStateStoreService
mapreduce.task.combine.progress.records=10000
s3.client-write-packet-size=65536
mapreduce.jobtracker.instrumentation=org.apache.hadoop.mapred.JobTrackerMetricsInst
dfs.replication=3
mapreduce.shuffle.transfer.buffer.size=131072
hadoop.security.group.mapping.ldap.directory.search.timeout=10000
dfs.datanode.available-space-volume-choosing-policy.balanced-space-threshold=10737418240
hadoop.work.around.non.threadsafe.getpwuid=false
yarn.app.mapreduce.client-am.ipc.max-retries-on-timeouts=3
yarn.nodemanager.address=${yarn.nodemanager.hostname}:0
mapreduce.tasktracker.taskcontroller=org.apache.hadoop.mapred.DefaultTaskController
mapreduce.tasktracker.indexcache.mb=10
yarn.scheduler.maximum-allocation-vcores=32
mapreduce.job.reduces=1
yarn.nodemanager.sleep-delay-before-sigkill.ms=250
yarn.timeline-service.address=${yarn.timeline-service.hostname}:10200
yarn.resourcemanager.configuration.provider-class=org.apache.hadoop.yarn.LocalConfigurationProvider
hadoop.security.kms.client.encrypted.key.cache.expiry=43200000
yarn.sharedcache.enabled=false
tfile.io.chunk.size=1048576
mapreduce.job.acl-modify-job= 
hadoop.registry.zk.session.timeout.ms=60000
fs.automatic.close=true
ha.health-monitor.sleep-after-disconnect.ms=1000
mapreduce.tasktracker.reduce.tasks.maximum=2
mapreduce.input.fileinputformat.list-status.num-threads=1
dfs.datanode.directoryscan.threads=1
dfs.datanode.directoryscan.interval=21600
dfs.namenode.acls.enabled=false
dfs.client.short.circuit.replica.stale.threshold.ms=1800000
hadoop.http.authentication.token.validity=36000
fs.s3.block.size=67108864
ha.failover-controller.graceful-fence.rpc-timeout.ms=5000
mapreduce.tasktracker.local.dir.minspacekill=0
dfs.namenode.resource.du.reserved=104857600
mapreduce.jobhistory.cleaner.interval-ms=86400000
dfs.namenode.datanode.registration.ip-hostname-check=true
mapreduce.jobhistory.intermediate-done-dir=${yarn.app.mapreduce.am.staging-dir}/history/done_intermediate
mapreduce.jobtracker.http.address=0.0.0.0:50030
dfs.namenode.backup.http-address=0.0.0.0:50105
dfs.namenode.edits.noeditlogchannelflush=false
yarn.nodemanager.recovery.compaction-interval-secs=3600
mapreduce.reduce.shuffle.input.buffer.percent=0.70
mapreduce.map.maxattempts=4
yarn.http.policy=HTTP_ONLY
dfs.namenode.audit.loggers=default
hadoop.security.groups.cache.warn.after.ms=5000
io.serializations=org.apache.hadoop.io.serializer.WritableSerialization,org.apache.hadoop.io.serializer.avro.AvroSpecificSerialization,org.apache.hadoop.io.serializer.avro.AvroReflectSerialization
mapreduce.tasktracker.outofband.heartbeat=false
mapreduce.reduce.shuffle.read.timeout=180000
hadoop.security.crypto.buffer.size=8192
hadoop.http.cross-origin.allowed-methods=GET,POST,HEAD
mapreduce.reduce.skip.proc.count.autoincr=true
mapreduce.ifile.readahead.bytes=4194304
dfs.http.policy=HTTP_ONLY
hadoop.registry.secure=false
dfs.namenode.safemode.min.datanodes=0
dfs.client.file-block-storage-locations.num-threads=10
mapreduce.cluster.local.dir=${hadoop.tmp.dir}/mapred/local
mapreduce.tasktracker.report.address=127.0.0.1:0
dfs.namenode.secondary.https-address=0.0.0.0:50091
hadoop.kerberos.kinit.command=kinit
yarn.timeline-service.http-authentication.type=simple
dfs.block.access.token.lifetime=600
dfs.webhdfs.enabled=true
yarn.dispatcher.drain-events.timeout=300000
dfs.namenode.delegation.token.max-lifetime=604800000
dfs.namenode.avoid.write.stale.datanode=false
dfs.datanode.drop.cache.behind.writes=false
yarn.log-aggregation.retain-seconds=-1
mapreduce.job.complete.cancel.delegation.tokens=true
mapreduce.local.clientfactory.class.name=org.apache.hadoop.mapred.LocalClientFactory
yarn.resourcemanager.fail-fast=${yarn.fail-fast}
mapreduce.shuffle.connection-keep-alive.timeout=5
dfs.namenode.num.extra.edits.retained=1000000
yarn.scheduler.minimum-allocation-vcores=1
ipc.client.connect.max.retries.on.timeouts=45
yarn.timeline-service.client.retry-interval-ms=1000
yarn.timeline-service.client.max-retries=30
fs.s3n.block.size=67108864
mapreduce.job.map.output.collector.class=org.apache.hadoop.mapred.MapTask$MapOutputBuffer
ha.health-monitor.connect-retry-interval.ms=1000
mapreduce.shuffle.max.threads=0
nfs.exports.allowed.hosts=* rw
dfs.client.mmap.cache.size=256
mapreduce.tasktracker.map.tasks.maximum=2
io.file.buffer.size=4096
yarn.nodemanager.container-metrics.unregister-delay-ms=10000
dfs.client.datanode-restart.timeout=30
io.mapfile.bloom.size=1048576
hadoop.security.kms.client.authentication.retry-count=1
dfs.namenode.checkpoint.txns=1000000
ipc.client.connect.retry.interval=1000
dfs.client-write-packet-size=65536
mapreduce.reduce.shuffle.connect.timeout=180000
yarn.resourcemanager.fs.state-store.uri=${hadoop.tmp.dir}/yarn/system/rmstore
fs.swift.impl=org.apache.hadoop.fs.swift.snative.SwiftNativeFileSystem
hadoop.registry.zk.connection.timeout.ms=15000
dfs.cachereport.intervalMsec=10000
yarn.app.mapreduce.shuffle.log.backups=0
yarn.app.mapreduce.am.container.log.limit.kb=0
yarn.nodemanager.resourcemanager.minimum.version=NONE
ftp.blocksize=67108864
yarn.resourcemanager.address=null:8032
dfs.namenode.kerberos.principal.pattern=*
file.stream-buffer-size=4096
yarn.resourcemanager.scheduler.monitor.enable=false
mapreduce.job.ubertask.maxreduces=1
nfs.allow.insecure.ports=true
yarn.resourcemanager.nodemanager-connect-retries=10
yarn.sharedcache.nm.uploader.thread-count=20
ipc.client.idlethreshold=4000
ftp.stream-buffer-size=4096
yarn.sharedcache.client-server.address=0.0.0.0:8045
yarn.app.mapreduce.client.job.retry-interval=2000
dfs.client.failover.connection.retries.on.timeouts=0
dfs.namenode.replication.work.multiplier.per.iteration=2
hadoop.http.authentication.simple.anonymous.allowed=true
hadoop.security.authorization=false
yarn.client.nodemanager-connect.retry-interval-ms=10000
yarn.am.liveness-monitor.expiry-interval-ms=600000
fs.har.impl.disable.cache=true
yarn.nodemanager.linux-container-executor.resources-handler.class=org.apache.hadoop.yarn.server.nodemanager.util.DefaultLCEResourcesHandler
yarn.timeline-service.leveldb-timeline-store.read-cache-size=104857600
hadoop.security.authentication=simple
dfs.image.compression.codec=org.apache.hadoop.io.compress.DefaultCodec
mapreduce.task.files.preserve.failedtasks=false
dfs.client.read.shortcircuit.streams.cache.size=256
yarn.timeline-service.leveldb-timeline-store.path=${hadoop.tmp.dir}/yarn/timeline
mapreduce.job.reduce.slowstart.completedmaps=0.05
mapreduce.jobhistory.minicluster.fixed.ports=false
file.replication=1
yarn.resourcemanager.ha.automatic-failover.enabled=true
mapreduce.job.userlog.retain.hours=24
mapreduce.jobhistory.joblist.cache.size=20000
dfs.namenode.accesstime.precision=3600000
yarn.resourcemanager.work-preserving-recovery.enabled=true
dfs.namenode.fs-limits.max-xattrs-per-inode=32
io.mapfile.bloom.error.rate=0.005
yarn.resourcemanager.store.class=org.apache.hadoop.yarn.server.resourcemanager.recovery.FileSystemRMStateStore
dfs.image.transfer.timeout=60000
yarn.timeline-service.leveldb-state-store.path=${hadoop.tmp.dir}/yarn/timeline
nfs.wtmax=1048576
dfs.namenode.support.allow.format=true
fs.s3a.multipart.purge=false
dfs.secondary.namenode.kerberos.internal.spnego.principal=${dfs.web.authentication.kerberos.principal}
fs.s3a.connection.establish.timeout=5000
dfs.stream-buffer-size=4096
dfs.namenode.invalidate.work.pct.per.iteration=0.32f
yarn.nodemanager.container-executor.class=org.apache.hadoop.yarn.server.nodemanager.DefaultContainerExecutor
fs.s3a.multipart.purge.age=86400
yarn.resourcemanager.scheduler.client.thread-count=50
dfs.namenode.top.enabled=true
yarn.app.mapreduce.shuffle.log.separate=true
hadoop.security.kms.client.encrypted.key.cache.low-watermark=0.3f
fs.s3a.fast.buffer.size=1048576
hadoop.user.group.static.mapping.overrides=dr.who=;
ipc.maximum.data.length=67108864
tfile.fs.input.buffer.size=262144
dfs.client.cached.conn.retry=3
hadoop.http.authentication.type=simple
dfs.namenode.list.encryption.zones.num.responses=100
mapreduce.map.cpu.vcores=1
dfs.namenode.path.based.cache.refresh.interval.ms=30000
dfs.namenode.decommission.interval=30
dfs.namenode.fs-limits.max-directory-items=1048576
yarn.resourcemanager.zk-retry-interval-ms=1000
ftp.bytes-per-checksum=512
dfs.ha.log-roll.period=120
dfs.user.home.dir.prefix=/user
ipc.client.fallback-to-simple-auth-allowed=false
yarn.nodemanager.pmem-check-enabled=true
yarn.nodemanager.remote-app-log-dir=/tmp/logs
dfs.namenode.inotify.max.events.per.rpc=1000
mapreduce.task.profile.maps=0-2
mapreduce.shuffle.ssl.file.buffer.size=65536
mapreduce.tasktracker.healthchecker.script.timeout=600000
yarn.timeline-service.webapp.https.address=${yarn.timeline-service.hostname}:8190
yarn.nodemanager.resource.percentage-physical-cpu-limit=100
yarn.app.mapreduce.am.command-opts=-Xmx1024m
dfs.namenode.fs-limits.max-xattr-size=16384
dfs.datanode.http.address=0.0.0.0:50075
yarn.resourcemanager.amlauncher.thread-count=50
dfs.namenode.blocks.per.postponedblocks.rescan=10000
yarn.sharedcache.nm.uploader.replication.factor=10
hadoop.registry.zk.root=/registry
hadoop.jetty.logs.serve.aliases=true
yarn.client.failover-proxy-provider=org.apache.hadoop.yarn.client.ConfiguredRMFailoverProxyProvider
mapreduce.jobhistory.admin.acl=*
mapreduce.job.reducer.unconditional-preempt.delay.sec=300
hadoop.http.cross-origin.max-age=1800
yarn.app.mapreduce.am.hard-kill-timeout-ms=10000
yarn.nodemanager.remote-app-log-dir-suffix=logs
mapreduce.jobhistory.principal=jhs/_HOST@REALM.TLD
yarn.resourcemanager.webapp.address=${yarn.resourcemanager.hostname}:8088
mapreduce.jobhistory.recovery.enable=false
yarn.sharedcache.store.in-memory.check-period-mins=720
nfs.mountd.port=4242
mapreduce.reduce.merge.inmem.threshold=1000
fs.df.interval=60000
yarn.timeline-service.enabled=false
mapreduce.jobtracker.jobhistory.lru.cache.size=5
hadoop.http.cross-origin.allowed-headers=X-Requested-With,Content-Type,Accept,Origin
mapreduce.task.profile=false
yarn.nodemanager.hostname=0.0.0.0
dfs.namenode.num.checkpoints.retained=2
mapreduce.job.queuename=default
mapreduce.jobhistory.max-age-ms=604800000
mapreduce.job.token.tracking.ids.enabled=false
yarn.nodemanager.localizer.client.thread-count=5
yarn.sharedcache.uploader.server.thread-count=50
dfs.client.mmap.retry.timeout.ms=300000
mapreduce.jobhistory.move.thread-count=3
dfs.permissions.enabled=true
dfs.blockreport.split.threshold=1000000
fs.AbstractFileSystem.hdfs.impl=org.apache.hadoop.fs.Hdfs
dfs.datanode.balance.bandwidthPerSec=1048576
dfs.block.scanner.volume.bytes.per.second=1048576
hadoop.http.filter.initializers=org.apache.hadoop.http.lib.StaticUserWebFilter
yarn.timeline-service.http-authentication.simple.anonymous.allowed=true
ipc.client.rpc-timeout.ms=0
dfs.default.chunk.view.size=32768
yarn.sharedcache.client-server.thread-count=50
yarn.resourcemanager.resource-tracker.address=${yarn.resourcemanager.hostname}:8031
mapreduce.jobhistory.datestring.cache.size=200000
mapreduce.task.profile.params=-agentlib:hprof=cpu=samples,heap=sites,force=n,thread=y,verbose=n,file=%s
dfs.namenode.decommission.blocks.per.interval=500000
dfs.namenode.handler.count=10
dfs.image.transfer.bandwidthPerSec=0
rpc.metrics.quantile.enable=false
mapreduce.jobtracker.expire.trackers.interval=600000
mapreduce.task.timeout=600000
yarn.app.mapreduce.client.max-retries=3
yarn.nodemanager.resource.memory-mb=8192
yarn.nodemanager.disk-health-checker.min-healthy-disks=0.25
dfs.datanode.failed.volumes.tolerated=0
yarn.timeline-service.handler-thread-count=10
ipc.server.listen.queue.size=128
fs.s3a.threads.max=256
yarn.resourcemanager.connect.max-wait.ms=900000
mapreduce.fileoutputcommitter.algorithm.version=1
mapreduce.framework.name=local
mapreduce.map.skip.proc.count.autoincr=true
mapreduce.job.max.split.locations=10
yarn.resourcemanager.scheduler.class=org.apache.hadoop.yarn.server.resourcemanager.scheduler.capacity.CapacityScheduler
yarn.resourcemanager.system-metrics-publisher.enabled=false
dfs.blocksize=134217728
yarn.sharedcache.nested-level=3
mapreduce.shuffle.connection-keep-alive.enable=false
fs.s3a.threads.keepalivetime=60
fs.s3a.connection.timeout=50000
file.client-write-packet-size=65536
ha.failover-controller.cli-check.rpc-timeout.ms=20000
ha.zookeeper.acl=world:anyone:rwcda
dfs.namenode.write.stale.datanode.ratio=0.5f
dfs.encrypt.data.transfer=false
ipc.client.ping=true
dfs.datanode.shared.file.descriptor.paths=/dev/shm,/tmp
mapreduce.input.lineinputformat.linespermap=1
yarn.resourcemanager.delayed.delegation-token.removal-interval-ms=30000
yarn.nodemanager.localizer.fetch.thread-count=4
dfs.client.failover.max.attempts=15
yarn.resourcemanager.scheduler.address=${yarn.resourcemanager.hostname}:8030
yarn.nodemanager.webapp.cross-origin.enabled=false
dfs.client.read.shortcircuit.streams.cache.expiry.ms=300000
yarn.timeline-service.leveldb-timeline-store.start-time-write-cache-size=10000
yarn.nodemanager.health-checker.script.timeout-ms=1200000
yarn.resourcemanager.fs.state-store.num-retries=0
hadoop.ssl.require.client.cert=false
hadoop.security.uid.cache.secs=14400
mapreduce.jobhistory.keytab=/etc/security/keytab/jhs.service.keytab
dfs.client.read.shortcircuit.skip.checksum=false
yarn.resourcemanager.ha.automatic-failover.zk-base-path=/yarn-leader-election
mapreduce.shuffle.ssl.enabled=false
mapreduce.reduce.log.level=INFO
hadoop.registry.rm.enabled=false
mapreduce.tasktracker.dns.interface=default
yarn.resourcemanager.system-metrics-publisher.dispatcher.pool-size=10
mapreduce.job.speculative.speculative-cap-running-tasks=0.1
dfs.datanode.block.id.layout.upgrade.threads=12
dfs.datanode.use.datanode.hostname=false
dfs.client.context=default
yarn.resourcemanager.ha.enabled=false
dfs.namenode.delegation.token.renew-interval=86400000
dfs.blockreport.intervalMsec=21600000
fs.s3a.multipart.threshold=2147483647
hadoop.http.cross-origin.enabled=false
mapreduce.reduce.shuffle.memory.limit.percent=0.25
dfs.https.server.keystore.resource=ssl-server.xml
io.map.index.skip=0
mapreduce.job.hdfs-servers=${fs.defaultFS}
mapreduce.jobtracker.taskscheduler=org.apache.hadoop.mapred.JobQueueTaskScheduler
dfs.namenode.kerberos.internal.spnego.principal=${dfs.web.authentication.kerberos.principal}
yarn.resourcemanager.state-store.max-completed-applications=${yarn.resourcemanager.max-completed-applications}
mapreduce.map.output.compress=false
hadoop.security.kms.client.encrypted.key.cache.num.refill.threads=2
fs.s3n.multipart.uploads.block.size=67108864
mapreduce.task.merge.progress.records=10000
dfs.datanode.dns.interface=default
map.sort.class=org.apache.hadoop.util.QuickSort
yarn.nodemanager.aux-services.mapreduce_shuffle.class=org.apache.hadoop.mapred.ShuffleHandler
tfile.fs.output.buffer.size=262144
fs.du.interval=600000
dfs.client.failover.connection.retries=0
dfs.namenode.top.window.num.buckets=10
fs.s3a.buffer.dir=${hadoop.tmp.dir}/s3a
mapreduce.reduce.shuffle.retry-delay.max.ms=60000
yarn.sharedcache.uploader.server.address=0.0.0.0:8046
mapreduce.client.progressmonitor.pollinterval=1000
yarn.app.mapreduce.shuffle.log.limit.kb=0
dfs.datanode.max.locked.memory=0
dfs.namenode.retrycache.expirytime.millis=600000
dfs.datanode.scan.period.hours=504
dfs.client.block.write.replace-datanode-on-failure.best-effort=false
mapreduce.jobhistory.move.interval-ms=180000
dfs.ha.fencing.ssh.connect-timeout=30000
yarn.nodemanager.log-aggregation.roll-monitoring-interval-seconds=-1
dfs.namenode.fs-limits.max-component-length=255
hadoop.registry.zk.quorum=localhost:2181
hadoop.http.cross-origin.allowed-origins=*
dfs.namenode.enable.retrycache=true
dfs.datanode.du.reserved=0
yarn.timeline-service.state-store-class=org.apache.hadoop.yarn.server.timeline.recovery.LeveldbTimelineStateStore
dfs.datanode.ipc.address=0.0.0.0:50020
hadoop.registry.system.acls=sasl:yarn@, sasl:mapred@, sasl:hdfs@
dfs.client.block.write.replace-datanode-on-failure.policy=DEFAULT
dfs.namenode.path.based.cache.retry.interval.ms=30000
hadoop.security.crypto.cipher.suite=AES/CTR/NoPadding
dfs.ha.tail-edits.period=60
mapreduce.task.profile.reduce.params=${mapreduce.task.profile.params}
yarn.timeline-service.generic-application-history.max-applications=10000
hadoop.registry.jaas.context=Client
yarn.resourcemanager.hostname=0.0.0.0
mapreduce.reduce.memory.mb=1024
hadoop.security.group.mapping.ldap.search.filter.group=(objectClass=group)
hadoop.http.authentication.kerberos.principal=HTTP/_HOST@LOCALHOST
hadoop.security.group.mapping.ldap.search.filter.user=(&(objectClass=user)(sAMAccountName={0}))
yarn.nodemanager.disk-health-checker.min-free-space-per-disk-mb=0
dfs.namenode.edits.dir=${dfs.namenode.name.dir}
yarn.client.failover-retries-on-socket-timeouts=0
dfs.namenode.decommission.max.concurrent.tracked.nodes=100
mapreduce.client.completion.pollinterval=5000
dfs.namenode.name.dir.restore=false
dfs.namenode.secondary.http-address=0.0.0.0:50090
mapreduce.jobhistory.recovery.store.leveldb.path=${hadoop.tmp.dir}/mapred/history/recoverystore
yarn.sharedcache.store.class=org.apache.hadoop.yarn.server.sharedcachemanager.store.InMemorySCMStore
s3.bytes-per-checksum=512
yarn.nodemanager.windows-container.cpu-limit.enabled=false
yarn.resourcemanager.webapp.https.address=${yarn.resourcemanager.hostname}:8090
yarn.nodemanager.vmem-pmem-ratio=2.1
dfs.namenode.checkpoint.period=3600
dfs.ha.automatic-failover.enabled=false
*/
}
