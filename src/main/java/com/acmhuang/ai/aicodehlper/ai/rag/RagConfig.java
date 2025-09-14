package com.acmhuang.ai.aicodehlper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


/**
 * @author Acmhuang
 * @date 2025/09/14 17:31
 **/
@Configuration
public class RagConfig {

    @Resource
    private EmbeddingStore<TextSegment> embeddingStore;

    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    @Bean
    public ContentRetriever contentRetriever() {
        //1. 加载文档
        List<Document> document = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        //2. 文档切割，每个文档按照段落进行分割，最多1000个字符，每次最多重叠200个字符
        DocumentByParagraphSplitter documentByParagraphSplitter =
                new DocumentByParagraphSplitter(1000, 200);
        //3.自定义文档加载器，把文档切片转换成向量并保存在向量数据库中
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                //为每个切割后的文档碎片添加文档名称作为元信息，提高文档质量
                .textSegmentTransformer(textSegment -> {
                    return TextSegment.from(textSegment.metadata().getString("file_name")
                            + "\n" + textSegment.text(), textSegment.metadata());
                })
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                .build();
        //加载文档
        ingestor.ingest(document);
        //4. 自定义内容加载器
        EmbeddingStoreContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(embeddingStore)
                // 最多返回5个结果
                .maxResults(5)
                .minScore(0.75)
                .build();
        return retriever;
    }

}