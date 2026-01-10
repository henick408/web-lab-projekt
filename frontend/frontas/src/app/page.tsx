"use client";

import Header from "./_components/header";
import SideMenu from "./_components/sideMenu";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import Post from "./_components/post";
import PostPostForm from "./_components/postPostForm";

type PostProps = {
  username: string;
  title: string;
  content: string;
  category: {
    name: string;
    id: number;
  };
  createdAt: string;
  updatedAt: string;
  id: number;
};

export default function Home() {
  const [cat, setCat] = useState<number>(-1);
  const [showForm, setShowForm] = useState(false);

  const [posts, setPosts] = useState<PostProps[]>([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);

  const scrollPosRef = useRef(0);

  const handleCat = (data: number) => {
    setCat(data);
    setPosts([]);
    setPage(0);
    setHasMore(true);
  };

  const handleShowForm = () => {
    setShowForm((prev) => !prev);
  };

  useEffect(() => {
    const fetchPosts = async () => {
      setLoading(true);
      try {
        const res = await axios.get("http://localhost:8080/api/posts", {
          params: {
            page,
            size: 10,
            ...(cat !== -1 && { categoryid: cat }),
          },
        });

        const newPosts = res.data.content;

        setPosts((prev) => (page === 0 ? newPosts : [...prev, ...newPosts]));

        setHasMore(!res.data.last);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, [page, cat]);

  useEffect(() => {
    if (page === 0) return;

    window.scrollTo({
      top: scrollPosRef.current,
      behavior: "smooth",
    });
  }, [posts, page]);

  return (
    <div className="min-h-screen bg-zinc-50 cursor-default">
      <Header />

      <div className="flex justify-between w-full">
        <SideMenu
          sendCat={handleCat}
          onAddPost={handleShowForm}
          showForm={showForm}
        />

        <div className="w-full flex flex-col items-center">
          {showForm && <PostPostForm />}

          {loading && posts.length === 0 && <div>ładowańsko</div>}

          {!loading && posts.length === 0 && <div>Brak postów</div>}

          {posts.map((post) => (
            <div
              key={post.id}
              className="w-full mt-2 rounded flex flex-col items-center gap-5"
            >
              <Post post={post} />
            </div>
          ))}

          {hasMore && !loading && (
            <button
              onClick={() => {
                scrollPosRef.current = window.scrollY;
                setPage((prev) => prev + 1);
              }}
              style={{ fontFamily: "EB Garamond, serif" }}
              className="mt-6 px-8 py-3 bg-blue-200 rounded-xl text-xl text-zinc-800 m-5 hover:bg-blue-300"
            >
              Załaduj więcej
            </button>
          )}

          {loading && posts.length > 0 && (
            <div className="mb-5">ładowanie…</div>
          )}
        </div>
      </div>
    </div>
  );
}
