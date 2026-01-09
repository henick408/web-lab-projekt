"use client";
import Header from "./_components/header";
import SideMenu from "./_components/sideMenu";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import Post from "./_components/post";
import PostPostForm from "./_components/postPostForm";

export default function Home() {
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
  const [cat, setCat] = useState(-1);
  const [showForm, setShowForm] = useState(false);

  const [posts, setPosts] = useState<PostProps[]>([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const scrollPosRef = useRef(0);

  const handleCat = (data: number) => {
    setCat(data); // aktualizujemy stan w komponencie wyżej
    console.log(cat);
  };
  const handleShowForm = () => {
    setShowForm(!showForm);
  };

  useEffect(() => {
    const fetchPosts = async () => {
      setLoading(true);
      try {
        const res = await axios.get("http://localhost:8080/api/posts", {
          params: {
            page,
            size: 10,
          },
        });

        const newPosts = res.data.content;

        setPosts((prev) => [...prev, ...newPosts]);
        setHasMore(!res.data.last); // Spring Pageable
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, [page]);

  useEffect(() => {
    if (page === 0) return;

    window.scrollTo({
      top: scrollPosRef.current,
      behavior: "smooth",
    });
  }, [posts, page]);

  console.log(posts);

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
          {loading ? (
            <div>ładowańsko</div>
          ) : posts.length === 0 ? (
            <div>Brak postów</div>
          ) : (
            posts
              .filter((post) => cat === -1 || post.category.id === cat)
              .map((post) => (
                <div
                  key={post.id}
                  className="w-full mt-2 rounded flex flex-col items-center gap-5"
                >
                  <Post post={post} />
                </div>
              ))
          )}
          {hasMore && !loading && (
            <button
              onClick={() => {
                scrollPosRef.current = window.scrollY;
                setPage((prev) => prev + 1);
              }}
              style={{ fontFamily: "EB Garamond, serif" }}
              className="mt-6 px-8 py-3 bg-blue-200 rounded-xl text-xl text-zinc-800 m-5 hover:cursor-pointer hover:bg-blue-300"
            >
              Załaduj więcej
            </button>
          )}
        </div>
      </div>
    </div>
  );
}
