package com.n1ar4.agent.sourceResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SourceResult implements Serializable, Comparable<SourceResult> {
    public SourceResultType type;
    public String name;
    public String sourceClass;
    public String methodInfo;
    public ArrayList<UrlInfo> urlInfos = null;
    public ArrayList<String> description = null;

    public SourceResult() {
    }

    public SourceResult(SourceResultType type, String name, String sourceClass, ArrayList<UrlInfo> urlInfos) {
        this.type = type;
        this.name = name;
        this.sourceClass = sourceClass;
        this.urlInfos = urlInfos;
        this.methodInfo = null;
        this.description = null;
    }

    public SourceResult(SourceResultType type, String name, String sourceClass, String methodInfo, ArrayList<UrlInfo> urlInfos) {
        this(type, name, sourceClass, urlInfos);
        this.methodInfo = methodInfo;
    }

    public SourceResult(SourceResultType type, String name, String sourceClass, ArrayList<UrlInfo> urlInfos, ArrayList<String> description) {
        this(type, name, sourceClass, urlInfos);
        if (description != null && !description.isEmpty())
            this.description = description;
    }

    public SourceResult(SourceResultType type, String name, String sourceClass, String methodInfo, ArrayList<UrlInfo> urlInfos, ArrayList<String> description) {
        this(type, name, sourceClass, urlInfos, description);
        this.methodInfo = methodInfo;
    }

    public SourceResultType getType() {
        return type;
    }

    public void setType(SourceResultType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public void setSourceClass(String sourceClass) {
        this.sourceClass = sourceClass;
    }

    public String getMethodInfo() {
        return methodInfo;
    }

    public void setMethodInfo(String methodInfo) {
        this.methodInfo = methodInfo;
    }

    public ArrayList<UrlInfo> getUrlInfos() {
        return urlInfos;
    }

    public void setUrlInfos(ArrayList<UrlInfo> urlInfos) {
        this.urlInfos = urlInfos;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceResult targetSourceResult = (SourceResult) o;
        if (!targetSourceResult.type.equals(this.type))
            return false;
        if (targetSourceResult.name == null) {
            if (this.name != null) {
                return false;
            }
        } else if (!targetSourceResult.name.equals(this.name)) {
            return false;
        }

        if (!targetSourceResult.sourceClass.equals(this.sourceClass))
            return false;
        if (targetSourceResult.methodInfo != null) {
            if (!targetSourceResult.methodInfo.equals(this.methodInfo)) {
                return false;
            }
        } else {
            if (this.methodInfo != null)
                return false;
        }
        return targetSourceResult.getUrlInfos().toString().equals(this.getUrlInfos().toString());
    }

    private int hashResult = -1;

    private int type_hash(Object data) {
        if (data == null)
            return 0;
        else
            return data.hashCode();
    }

    public int hashCode() {
        if (hashResult == -1) {
            hashResult = 1;
            hashResult = 31 * hashResult + type_hash(type);
            hashResult = 31 * hashResult + type_hash(name);

            hashResult = 31 * hashResult + type_hash(sourceClass);
            hashResult = 31 * hashResult + (methodInfo == null ? "".hashCode() : methodInfo.hashCode());
            hashResult = 31 * hashResult + (urlInfos == null ? 0 : Arrays.hashCode(getUrlInfos().toArray()));
            hashResult = 31 * hashResult + (description == null ? 0 : Arrays.hashCode(getDescription().toArray()));
        }
        return hashResult;
    }

    @Override
    public int compareTo(SourceResult o) {
        if (this.type.ordinal() != o.type.ordinal()) {
            return this.type.ordinal() - o.type.ordinal();
        } else {
            return Arrays.toString(new ArrayList[]{this.urlInfos}).compareTo(Arrays.toString(new ArrayList[]{o.getUrlInfos()}));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Source Type : ").append(this.getType().toString()).append("\n");
        sb.append("\t Source Name : ").append(this.getName()).append("\n");
        sb.append("\t Source Class : ").append(this.getSourceClass()).append("\n");
        if (this.urlInfos != null && !this.urlInfos.isEmpty()) {
            sb.append("\t Source UrlInfo : \n");
            for (UrlInfo urlInfo : this.urlInfos) {
                sb.append("\t\t Url : ").append(urlInfo.getUrl()).append("\n");
                if (urlInfo.getDescrition().isEmpty())
                    continue;
                for (String oneLineDesc : urlInfo.descrition.split("\\|")) {
                    sb.append("\t\t\t desc : ").append(oneLineDesc).append("\n");
                }
            }
        }
        ArrayList<String> description = this.getDescription();
        if (description != null && !description.isEmpty()) {
            sb.append("\t Source Description : \n");
            for (String desc : description) {
                sb.append("\t\t ").append(desc).append("\n");
            }
        }
        return sb.toString();
    }

    public String generateUrlInfo() {
        StringBuilder sb = new StringBuilder();
        if (this.urlInfos != null && !this.urlInfos.isEmpty()) {
            for (UrlInfo urlInfo : this.urlInfos) {
                sb.append(urlInfo.getUrl());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}