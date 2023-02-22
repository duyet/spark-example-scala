1. Download Spark

```bash
curl -o ~/Downloads/spark-3.3.2-bin-hadoop3.tgz https://dlcdn.apache.org/spark/spark-3.3.2/spark-3.3.2-bin-hadoop3.tgz
tar zxvf ~/Downloads/spark-3.3.2-bin-hadoop3.tgz -C ~/Downloads
```

![](./.github/screenshots/download.png)

2. Build .jar

```bash
sbt package
```

![](./.github/screenshots/sbt-package.png)

3. Spark Submit

```bash
~/Downloads/spark-3.3.2-bin-hadoop3/bin/spark-submit --master "local[*]" target/scala-2.12/spark-example_2.12-0.1.0-SNAPSHOT.jar 9999
```

![](./.github/screenshots/spark-submit.png)
