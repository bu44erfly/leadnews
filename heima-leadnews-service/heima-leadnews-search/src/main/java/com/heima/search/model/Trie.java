package com.heima.search.model;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.var = ' ';
    }

    /**
     * 插入trie树
     * @param word
     */
    public void insert(String word) {
        TrieNode ws = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!ws.children.containsKey(c)) {
                ws.children.put(c, new TrieNode(c));
            }
            ws = ws.children.get(c);
        }
        ws.isWord = true;
    }

    /**
     * 查询trie树
     * @param prefix
     * @return
     */
    public List<String> startWith(String prefix) {
        List<String> match = new ArrayList<>();
        TrieNode ws = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!ws.children.containsKey(c)) return match;

            ws = ws.children.get(c);
        }

        // Search for all words starting with the given prefix
        collectWords(ws, new StringBuilder(prefix), match);
        return match;
    }

    // Helper method to collect all words from a given node  dfs
    private void collectWords(TrieNode node, StringBuilder prefix, List<String> result) {
        if (node.isWord) {
            result.add(prefix.toString());
        }

        for (char c : node.children.keySet()) {
            prefix.append(c);
            collectWords(node.children.get(c), prefix, result);
            prefix.deleteCharAt(prefix.length() - 1);  // Backtrack
        }
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("java");
        t.insert("java development kit");
        t.insert("bank");
        List<String> ret = t.startWith("ja");
        System.out.println(ret);
    }
}
